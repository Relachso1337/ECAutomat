const puppeteer = require('puppeteer');
const fs = require('fs/promises');

async function scrapeProduct(url) {
    const browser = await puppeteer.launch();
    const page = await browser.newPage();
    await page.goto(url);

    const [el] = await page.$x('//*[@id="tab-thu"]/div/div[1]/small');
    const menu = await el.getProperty('textContent');
    const menuTxt = await menu.jsonValue();

    const [el2] = await page.$x('//*[@id="tab-thu"]/div/div[2]/small');
    const menu2 = await el2.getProperty('textContent');
    const menuTxt2 = await menu2.jsonValue();

    const [el3] = await page.$x('//*[@id="tab-fri"]/div/div[3]/small');
    const menu3 = await el3.getProperty('textContent');
    const menuTxt3 = await menu3.jsonValue();

    var obj = {
        menu: []
     };

    obj.menu.push({id: 1, essen: menuTxt});
    obj.menu.push({id: 2, essen: menuTxt2});
    obj.menu.push({id: 3, essen: menuTxt3});

    var json = JSON.stringify(obj);

    fs.writeFile('menu.json', json, 'utf8');

    browser.close();
}

scrapeProduct('https://www.swfr.de/essen/mensen-cafes-speiseplaene/mensa-offenburg');