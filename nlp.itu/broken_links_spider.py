from scrapy.selector import HtmlXPathSelector
from scrapy.spiders import CrawlSpider, Rule
from scrapy.linkextractors.sgml import SgmlLinkExtractor
from scrapy.item import Item, Field
import scrapy
import config
from scrapy.linkextractors import LinkExtractor
from scrapy.http import Request



class BrokenItem(Item):
    url = Field()
    referer = Field()
    status = Field()


class BrokenLinksSpider(CrawlSpider):
    handle_httpstatus_list = [404]
    name = config.name
    allowed_domains = config.allowed_domains
    start_urls = config.start_urls


    def parse(self, response):
	print "status"+str(response.status)
	print "link"+str(response.request)
	if response.status == 404:
	    F=open("404links.txt","a")
	    F.write(response.url+"\n")
	    F.close()
	else:
            extractor = LinkExtractor(allow_domains=allowed_domains)
            links = extractor.extract_links(response)
            for link in links:
            	new_request = Request(link.url, callback=self.parse)
            	yield new_request

