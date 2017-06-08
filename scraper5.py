import scrapy
from scrapy import Selector
import html2text
import nltk
from scrapy.http import Request


#F = open("cabbar.txt","w") 


from bs4 import BeautifulSoup
class BrickSetSpider(scrapy.Spider):
    name = "brickset_spider"
    start_urls = ['https://fr.wikisource.org/w/index.php?search=%C3%A9toit&title=Sp%C3%A9cial%3ARecherche&fulltext=Rechercher&limit=18091&offset=0']


    def parse(self, response):
        SET_SELECTOR = '.mw-search-results'
        for brickset in response.css(SET_SELECTOR):
	    aa = brickset.extract()
	    #print sss
	    sel = Selector(text=aa,type="html")
       	    urlst = (sel.xpath('//li//@href')).extract()
	    #print urlst
	    for url in urlst:
                new_request = Request('https://fr.wikisource.org'+url, callback=self.parse2)
                yield new_request

    def parse2(self, response):
        SET_SELECTOR = '.mw-content-ltr'
        for brickset in response.css(SET_SELECTOR):
	    aa = brickset.extract()
	    sel2 = Selector(text=aa,type="html")
	    soup2 = BeautifulSoup(aa)
            F=open("cabbar.txt","a")
            F.write(soup2.text.encode('utf-8','ignore'))
	    if(len(soup2.text.encode('utf-8','ignore'))<10):
		F.write("problemmosssss")
	    F.close()
