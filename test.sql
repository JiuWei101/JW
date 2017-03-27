USE DocumentAcquisition

GO
ALTER PROCEDURE [dbo].[getEmailInfo]
	@startTime VARCHAR(15),
	@endTime VARCHAR(15),
	@fileType VARCHAR(10)
	

AS
BEGIN
	declare @temp int  -- 缓存游标值
	declare @nameList VARCHAR(200)  -- 缓存附件名称
	declare @nameStr VARCHAR(200)  -- 缓存附件名称
	@results VARCHAR(1000) output -- 结果

	declare order_cursor CURSOR FOR 
	select EmailId from EmailInfo where ReceivedTime>@startTime and ReceivedTime<@endTime and AttachmentNameList like CONCAT('%', @fileType, '%')

	open order_cursor
		WHILE @@FETCH_STATUS = 0
			BEGIN
				FETCH NEXT FROM order_cursor into @temp

				@nameList = select AttachmentNameList from EmailInfo where ReceivedTime>@startTime and ReceivedTime<@endTime and AttachmentNameList like CONCAT('%', @fileType, '%')

				declare name_cursor CURSOR FOR select Value from dbo.SplitString(@nameList, ';')
				open name_cursor
					WHILE @@FETCH_STATUS = 0
					 BEGIN
					 	FETCH NEXT FROM name_cursor into @nameStr

					 	if CHARINDEX(@fileType, @nameStr) > 0
					 		BEGIN
					 			@results = @temp + '/' + @nameStr + ';'
					 		END
					 END
				close name_cursor
				deallocate name_cursor
			END
	close order_cursor
	deallocate order_cursor

	select @results
END


exec [dbo].[getEmailInfo] '2010-01-01 00:00:00','2016-01-01 00:00:00','.csv'