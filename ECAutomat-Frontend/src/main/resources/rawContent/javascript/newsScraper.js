const puppeteer = require('puppeteer');
const http = require("http");
const fs = require('fs');


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
    const newsPage = await browser.newPage();
    await newsPage.goto(newsLinks[1]);
    await newsPage.setViewport({
        width: 1920,
        height: 1080
    })
    // image
    await newsPage.waitForSelector(".img-responsive");
    const newsImgContainer = await newsPage.$('.img-responsive');
    await newsImgContainer.screenshot({
        path: "../media/news/1.jpg"
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
    jsonResponse.imageDir = "../media/news/1.jpg";
    jsonResponse.headline = headline;
    jsonResponse.author = author;
    jsonResponse.date = date_;
    jsonResponse.url = newsLinks[0];

    fs.writeFile('../media/news/news.json', JSON.stringify(jsonResponse), function(err) {
        if(err) throw err;
    })

}

console.log(scrapeLinks());