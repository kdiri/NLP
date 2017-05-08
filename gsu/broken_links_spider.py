from scrapy.spiders import CrawlSpider, Rule
import scrapy
import config
from scrapy.linkextractors import LinkExtractor
from scrapy.http import Request


class BrokenLinksSpider(CrawlSpider):
    handle_httpstatus_list = [404]
    name = config.name
    allowed_domains = config.allowed_domains
    start_urls = config.start_urls


    def parse(self, response):
	if response.status == 404:
	    F=open(self.name+"_404links.txt","a")
	    F.write(response.url+"\n")
	    F.close()
	else:
            extractor = LinkExtractor(allow_domains=self.allowed_domains,deny_domains="uni.gsu.edu.tr")
            links = extractor.extract_links(response)
            for link in links:
            	new_request = Request(link.url, callback=self.parse)
            	yield new_request

