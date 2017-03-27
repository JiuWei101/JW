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
        #self.getText(soup,'Investment \nobjective')
        self.init(soup)
        self.getText(soup, 'Investment objective')
        print('--------- Shareholder fees data ---------')
        self.getTable(soup, 'Shareholder.*fees')
        print('--------- Annual fund data --------------')
        self.getTable(soup, 'Annual.*fund')
        print('--------- Expenses ----------------------')
        self.getTable(soup, 'Expenses')
        print('--------- Principal risks ---------------')
        self.getText(soup, 'Principal risks')
        print('--------- Credit and counterparty risk --')
        self.getText(soup, 'Credit and counterparty risk.')
        print('--------- Currency risk -----------------')
        self.getText(soup, 'Currency risk.')
        print('--------- Cybersecurity risk. -----------------')
        self.getText(soup, 'Cybersecurity risk.')

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

    def getTable(self, soup, title_name):
        shareholder_tag = soup.find(text=re.compile(title_name))
        if shareholder_tag is None:
            return

        shareholder_table = []
        next_tag = shareholder_tag.find_parent('table')
        if next_tag is None:
            print('This is not a table.')
            return
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
docPath = 'E:\\code\\documents\\121956075_487a573f-04b3-4489-8aa4-069734eb4804\\DM_DOC_1.htm'
#docPath = 'https://www.sec.gov/Archives/edgar/data/722574/000137949117000307/filing723.htm'
parser = ParserEtc(docPath)
parser.run()