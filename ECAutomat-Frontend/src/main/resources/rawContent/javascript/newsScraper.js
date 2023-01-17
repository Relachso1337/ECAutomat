const puppeteer = require('puppeteer');
const http = require("http");
const fs = require('fs');

/**
 * Helper Method for async for loop in scrapeLinks.
 * Scrapes 10 news from http://hs-offenburg.de and its subdomains
 */
async function* foo() {
    yield 0;
    yield 1;
    yield 2;
    yield 3;
    yield 4;
    yield 5;
    yield 6;
    yield 7;
    yield 8;
    yield 9;
    yield 10;
}

/**
 * Uses Node-Puppeteer to scrape for links and gets the news from the given URLs of http://hs-offenburg.de
 */
async function scrapeLinks() {
    const browser = await puppeteer.launch();
    const page = await browser.newPage();
    await page.goto("http://hs-offenburg.de");
    var links = await page.$$eval('a', as => as.map(a=>a.href));  // gets All links from Homepage
    var newsLinks = [];
    links.forEach(function(e) {
        if (e.includes("/news-detailseite/article") && !newsLinks.includes(e)) {
            newsLinks.push(e);
        }
    });
    // Creates new Page object of Newspage
    for await (const news of foo()) {
        const newsPage = await browser.newPage();
        // const randomNbr = Math.floor(Math.random() * ((newsLinks.length-1)+ 1))  // Math.floor(Math.random() * (max - min + 1) + min)
        await newsPage.goto(newsLinks[news]);
        await newsPage.setViewport({
            width: 1920,
            height: 1080
        })
        // image
        await newsPage.waitForSelector(".img-responsive");
        const newsImgContainer = await newsPage.$('.img-responsive');
        await newsImgContainer.screenshot({
            path: `../media/news/${news}.jpg`
        })
        // Headline 
        var headlineList = await newsPage.$x('//h3[@itemprop="headline"]');
        var headline = await newsPage.evaluate(el => el.textContent, headlineList[0])
        // Author
        var authorList= await newsPage.$x('//span[@itemprop="author"]//span[@itemprop="name"]');
        var author = await newsPage.evaluate(el => el.textContent, authorList[0]);
        // Date
        var dateList = await newsPage.$x('//time[@itemprop="datePublished"]');
        var date_ = await newsPage.evaluate(el => el.textContent, dateList[0]);
        date_.replace(/(\r\n|\n|\r\t)/gm, "");
        // Content
        var contentList = await newsPage.$x("//div[@class='news-text-wrap']//p");
        newContents = []
        for(var i = 0; i < contentList.length; i++) {
            var content = await (await contentList[i].getProperty("textContent")).jsonValue();
            if (content != null || content != " " || content != "" || content != "\n" || content != "\r" || content != ' ')
                newContents.push(content);
        }
        
        var jsonResponse = {"paragraphs": []}
        newContents.forEach(el => {
            jsonResponse.paragraphs.push({"content": el})
        });
        jsonResponse.imageDir = `../media/news/${news}.jpg`;
        jsonResponse.headline = headline;
        jsonResponse.author = author;
        jsonResponse.date = date_;
        jsonResponse.url = newsLinks[0];
    
        fs.writeFile(`../media/news/news${news}.json`, JSON.stringify(jsonResponse), function(err) {
            if(err) throw err;
        })
    }
    browser.close();
}

Promise.all([scrapeLinks()]);