# -*- coding: utf-8 -*-
import scrapy
from scrapy import Selector
import html2text
import nltk
from scrapy.http import Request
import urllib

#F = open("cabbar.txt","w") 


from bs4 import BeautifulSoup
class BrickSetSpider(scrapy.Spider):
    cabbar = 'étoit'
    name = "brickset_spider"
    start_urls = ['https://fr.wikisource.org/w/index.php?search='+urllib.quote(cabbar)+'&title=Sp%C3%A9cial%3ARecherche&fulltext=Rechercher&limit=5000&offset=0']


    def parse(self, response):
        SET_SELECTOR2 = '.results-info'
	for cabbar in response.css(SET_SELECTOR2):
	    aa = cabbar.extract()
	    sel = Selector(text=aa,type="html")
       	    za = (sel.xpath('//div[@class="results-info"]//@data-mw-num-results-total')).extract()
	    for x in range(1,int(int(za[0])/5000)):
	    	new_request = Request('https://fr.wikisource.org/w/index.php?search='+urllib.quote(self.cabbar)+'&title=Sp%C3%A9cial%3ARecherche&fulltext=Rechercher&limit='+str(5000*x)+'&offset='+str(x*5000), callback=self.parse)
            	yield new_request
	

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
            F=open(self.cabbar+".txt","a")
            F.write(soup2.text.encode('utf-8','ignore'))
	    if(len(soup2.text.encode('utf-8','ignore'))<10):
		F.write("problemmosssss")
	    F.close()
