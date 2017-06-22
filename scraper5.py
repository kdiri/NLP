# -*- coding: utf-8 -*-

# to run : scrapy runspider scraper5.py -a word='word' -s JOBDIR='your_folder'
# example : scrapy runspider scraper5.py -a word=étoit -s JOBDIR=dir

import scrapy
from scrapy import Selector
import html2text
from scrapy.http import Request
import urllib
import sys
from bs4 import BeautifulSoup


class WikiSpider(scrapy.Spider):
    name = "wikisource_spider"

    def __init__(self, word='', domain=None, *args, **kwargs):
        super(WikiSpider, self).__init__(*args, **kwargs)
        self.word = word
        self.start_urls = ['https://fr.wikisource.org/w/index.php?search='+urllib.quote(self.word)+'&title=Sp%C3%A9cial%3ARecherche&fulltext=Rechercher&limit=5000&offset=0']


    def parse(self, response):
        SET_SELECTOR2 = '.results-info'
        for cabbar in response.css(SET_SELECTOR2):
       	    za = cabbar.xpath('//div[@class="results-info"]/@data-mw-num-results-total').extract()
            for x in range(1,int(int(za[0])/5000)+1):
                new_request = Request('https://fr.wikisource.org/w/index.php?search='+urllib.quote(self.word)+'&title=Sp%C3%A9cial%3ARecherche&fulltext=Rechercher&limit=5000&offset='+str(x*5000), callback=self.parse)
                yield new_request

        SET_SELECTOR = '.mw-search-results'
        for brickset in response.css(SET_SELECTOR):
       	    urlst = brickset.xpath('//li//a/@href').extract()
            for url in urlst:
                new_request = Request('https://fr.wikisource.org'+url, callback=self.parse2)
                yield new_request

    def parse2(self, response):
        SET_SELECTOR = '.mw-content-ltr'
        for brickset in response.css(SET_SELECTOR):
            aa = brickset.extract()
            soup2 = BeautifulSoup(aa)
            F=open(self.word+".txt","a")
            F.write(soup2.text.encode('utf-8','ignore').split('►')[-1])
            F.write("==============================================")
            if(len(soup2.text.encode('utf-8','ignore'))<10):
                F.write("problemmosssss")
            F.close()
