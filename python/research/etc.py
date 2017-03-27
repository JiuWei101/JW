from bs4 import BeautifulSoup
from bs4.element import (Tag,NavigableString)
import urllib.request
import re
from bs4.diagnose import diagnose

class ParserEtc(object):
    def __init__(self, path):
        self.path = path

    def run(self):
        resp = open(self.path, encoding='utf-8')
        soup = BeautifulSoup(resp, 'lxml')
        self.init(soup)
        self.getAllTableFromNext(soup, 'Assets and.*Liabilities')

    def init(self, soup):
        # remove all '\n' char
        html = soup.find('html')
        if html is not None:
            next_tag = html
            while True:
                next_tag = next_tag.next_element
                if isinstance(next_tag, str):
                    continue

                if isinstance(next_tag, NavigableString):
                    next_tag = next_tag.replace('\n', '')
                    continue

                if isinstance(next_tag, Tag):
                    if next_tag.string is not None:
                        next_tag.string = next_tag.string.replace('\n', '')
                    continue

                if next_tag is None:
                    break

    def getText(self, soup, title_name):
        invest = soup.find(text=title_name)
        if invest is None:
            return

        next_tag = invest
        while True:
            next_tag = next_tag.next_element
            if next_tag is None:
                continue
            if isinstance(next_tag, NavigableString) and next_tag == '\n':
                continue
            if isinstance(next_tag, Tag) and next_tag.text == '\n':
                continue

            if isinstance(next_tag, NavigableString):
                print(next_tag.replace('\n',''))
            if isinstance(next_tag, Tag):
                print(next_tag.text.replace('\n',''))
            break

    def getAllTableFromPro(self, soup, title_name):
        tags = soup.find_all(text=re.compile(title_name))
        if tags is not None:
            for tag in tags:
                self.parseTable(soup, tag, False)

    def getAllTableFromNext(self, soup, title_name):
        tags = soup.find_all(text=re.compile(title_name))
        if tags is not None:
            for tag in tags:
                parent_tag = tag.find_parent('table')
                if parent_tag is not None:
                    statement_tag = parent_tag.previous_element.previous_element
                    if statement_tag.find('Statement \nof') != -1:
                        print('=============================================================')
                        #self.parseTable(soup, statement_tag.previous_element, True)
                        self.parseAssetsTable(statement_tag.previous_element)
                        self.parseOperationsTable(statement_tag.previous_element)
                        self.parseHighlightTable(statement_tag.previous_element)

    def getTableTag(self, current_tag, isNext):
        if current_tag is None:
            return
        next_tag = current_tag
        if isNext:
            while True:
                next_tag = next_tag.next_element
                if next_tag is None:
                    break
                if isinstance(next_tag, Tag) and next_tag.name.lower() == 'table':
                    break
        else:
            next_tag = next_tag.find_parent('table')
        return next_tag

    def parseAssetsTable(self, current_tag):
        this_tag = current_tag
        key_words = {'Statement':False,'Assets':False,'Liabilities':False}
        key_words_next = {'Statement': False, 'Operations': False}
        while True:
            this_tag = this_tag.next_element
            if this_tag is None:
                break
            if isinstance(this_tag, Tag):
                if this_tag.text.find('Statement') != -1:
                    key_words['Statement'] = True
                if this_tag.text.find('Assets') != -1:
                    key_words['Assets'] = True
                if this_tag.text.find('Liabilities') != -1:
                    key_words['Liabilities'] = True

                if this_tag.text.find('Statement') != -1:
                    key_words_next['Statement'] = True
                if this_tag.text.find('Operations') != -1:
                    key_words_next['Operations'] = True

            if isinstance(this_tag, NavigableString):
                if this_tag.find('Statement') != -1:
                    key_words['Statement'] = True
                if this_tag.find('Assets') != -1:
                    key_words['Assets'] = True
                if this_tag.find('Liabilities') != -1:
                    key_words['Liabilities'] = True

                if this_tag.find('Statement') != -1:
                    key_words_next['Statement'] = True
                if this_tag.find('Operations') != -1:
                    key_words_next['Operations'] = True

            if key_words_next['Statement'] and key_words_next['Operations']:
                break

            if key_words['Statement'] and key_words['Assets'] and key_words['Liabilities']:
                table_tag = self.getTableTag(this_tag, True)
                if table_tag is None:
                    break
                print('---------- get a assets table------------')
                self.getDataFromTable(table_tag)
                key_words['Statement'] = False
                key_words['Assets'] = False
                key_words['Liabilities'] = False

    def parseOperationsTable(self, current_tag):
        this_tag = current_tag
        key_words = {'Statement':False,'Operations':False}
        key_words_next = {'Financial': False, 'Highlights': False}
        while True:
            this_tag = this_tag.next_element
            if this_tag is None:
                break
            if isinstance(this_tag, Tag):
                if this_tag.text.find('Statement') != -1:
                    key_words['Statement'] = True
                if this_tag.text.find('Operations') != -1:
                    key_words['Operations'] = True

                if this_tag.text.find('Financial') != -1:
                    key_words_next['Financial'] = True
                if this_tag.text.find('Highlights') != -1:
                    key_words_next['Highlights'] = True

            if isinstance(this_tag, NavigableString):
                if this_tag.find('Statement') != -1:
                    key_words['Statement'] = True
                if this_tag.find('Operations') != -1:
                    key_words['Operations'] = True

                if this_tag.find('Financial') != -1:
                    key_words_next['Financial'] = True
                if this_tag.find('Highlights') != -1:
                    key_words_next['Highlights'] = True

            if key_words_next['Financial'] and key_words_next['Highlights']:
                break

            if key_words['Statement'] and key_words['Operations']:
                table_tag = self.getTableTag(this_tag, True)
                if table_tag is None:
                    break
                print('---------- get a Operations table------------')
                self.getDataFromTable(table_tag)
                key_words['Statement'] = False
                key_words['Operations'] = False

    def parseHighlightTable(self, current_tag):
        this_tag = current_tag
        key_words = {'Financial':False,'Highlights':False}
        while True:
            this_tag = this_tag.next_element
            if this_tag is None:
                break
            if isinstance(this_tag, Tag):
                if this_tag.text.find('Financial') != -1:
                    key_words['Financial'] = True
                if this_tag.text.find('Highlights') != -1:
                    key_words['Highlights'] = True
            if isinstance(this_tag, NavigableString):
                if this_tag.find('Financial') != -1:
                    key_words['Financial'] = True
                if this_tag.find('Highlights') != -1:
                    key_words['Highlights'] = True

            if key_words['Financial'] and key_words['Highlights']:
                table_tag = self.getTableTag(this_tag, True)
                if table_tag is None:
                    break
                print('---------- get a Highlights table------------')
                self.getDataFromTable(table_tag)
                key_words['Financial'] = False
                key_words['Highlights'] = False

    def getDataFromTable(self,table_tag):
        if table_tag is None or table_tag.name.lower() != 'table':
            print('This is not a table.')
            return

        shareholder_table = []
        next_tag = table_tag
        while True:
            next_tag = next_tag.next_element
            if next_tag is None:
                continue
            if isinstance(next_tag, NavigableString):
                continue
            if next_tag.name.lower() == 'tr':
                tr_list = []
                td_tag = next_tag
                while True:
                    td_tag = td_tag.next_element
                    if td_tag is None:
                        continue
                    if td_tag.string == '\n':
                        continue
                    if isinstance(td_tag, Tag) and td_tag.name.lower() == 'tr':
                        break
                    if isinstance(td_tag, Tag) and td_tag.name.lower() != 'td':
                        continue
                    if isinstance(td_tag, NavigableString):
                        continue
                    tr_list.append(td_tag.text.replace('\n', ''))

                shareholder_table.append(repr(tr_list))

            if isinstance(next_tag, Tag) and next_tag.name.lower() == 'table':
                break

        self.printTable(shareholder_table)

    def printTable(self, table_list):
        for tr in table_list:
            print(tr)


#---- main -----
docPath = 'D:\\Document\\research\\documents\\AnnualReport\\114257419__2\\Temp_DM_DOC_2.htm'
parser = ParserEtc(docPath)
parser.run()