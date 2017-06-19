# -*- coding: utf-8 -*-
import scrapy
from scrapy import Selector
import html2text
import nltk
from scrapy.http import Request
import urllib
import re
import sys

from bs4 import BeautifulSoup
class BrickSetSpider(scrapy.Spider):
    word = str(sys.argv)[0]
    name = "brickset_spider"
    start_urls = ['https://fr.wikisource.org/w/index.php?search='+urllib.quote(word)+'&title=Sp%C3%A9cial%3ARecherche&fulltext=Rechercher&limit=5000&offset=0']


    def parse(self, response):
        SET_SELECTOR2 = '.results-info'
	for rslt_info in response.css(SET_SELECTOR2):
	    rslt_info_txt = rslt_info.extract()
	    sel = Selector(text=rslt_info_txt,type="html")
       	    result_number = (sel.xpath('//div[@class="results-info"]//@data-mw-num-results-total')).extract()
	    for cnt in range(1,int(int(result_number[0])/5000)):
	    	new_request = Request('https://fr.wikisource.org/w/index.php?search='+urllib.quote(self.word)+'&title=Sp%C3%A9cial%3ARecherche&fulltext=Rechercher&limit='+str(5000*cnt)+'&offset='+str(cnt*5000), callback=self.parse)
            	yield new_request
	

        SET_SELECTOR = '.mw-search-results'
        for brickset in response.css(SET_SELECTOR):
	    aa = brickset.extract()
	    sel = Selector(text=aa,type="html")
       	    urlst = (sel.xpath('//li//@href')).extract()
	    for url in urlst:
                new_request = Request('https://fr.wikisource.org'+url, callback=self.parse2)
                yield new_request

    def parse2(self, response):
        SET_SELECTOR = '.mw-content-ltr'
        for brickset in response.css(SET_SELECTOR):
	    aa = brickset.extract()
	    soup2 = BeautifulSoup(aa)
            F=open(self.cabbar+".txt","a")
            F.write(soup2.text.encode('utf-8','ignore').split('►')[-1])
            F.write("==============================================")
	    if(len(soup2.text.encode('utf-8','ignore'))<10):
		F.write("problemmosssss")
	    F.close()
