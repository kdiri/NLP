from scrapy.spiders import CrawlSpider, Rule
import scrapy
import config
from scrapy.linkextractors import LinkExtractor
from scrapy.http import Request


class BrokenLinksSpider(CrawlSpider):
    handle_httpstatus_list = [404]
    name = config.name
    start_urls = config.start_urls

    def parse(self, response):
        flag = response.meta.get('flag')
        reference = response.meta.get('reference')
	if response.status == 404:
	    F=open(self.name+"_404links.txt","a")
	    F.write(response.url)
            if reference!= None:
                F.write("\t"+reference)
            F.write("\n")
	    F.close()
	if flag or flag==None:
            extractor = LinkExtractor(deny_domains="")
            links = extractor.extract_links(response)
            for link in links:
                if link.url[:24]=="https://spark.apache.org" or link.url[:23]=="http://spark.apache.org":
                    new_request = Request(link.url, callback=self.parse,meta={'flag': True,'reference': response.url})
                else:
                    new_request = Request(link.url, callback=self.parse,meta={'flag': False,'reference': response.url})
                yield new_request
