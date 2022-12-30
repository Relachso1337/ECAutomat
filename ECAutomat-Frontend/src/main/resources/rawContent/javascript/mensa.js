const puppeteer = require('puppeteer');
var fs = require('fs');

async function scrapeProduct(url) {
    const browser = await puppeteer.launch();
    const page = await browser.newPage();
    await page.goto(url);

    var obj = {menu: []};

    //MONTAG

    //MENÜ 1
    const [el] = await page.$x('//*[@id="tab-mon"]/div/div[1]/small');

    if(el != null) {
        const menu = await el.getProperty('textContent');
        const menuTxt = await menu.jsonValue();

        obj.menu.push({tag: "Montag", essen: menuTxt.replace(/([A-Z])/g, ' $1').trim()});
    } else {
        obj.menu.push({tag: "Montag", essen: "-"});
    }

    //MENÜ 2
    const [el2] = await page.$x('//*[@id="tab-mon"]/div/div[2]/small');

    if(el2 != null) {
        const menu2 = await el2.getProperty('textContent');
        const menuTxt2 = await menu2.jsonValue();

        obj.menu.push({tag: "Montag", essen: menuTxt2.replace(/([A-Z])/g, ' $1').trim()});
    } else {
        obj.menu.push({tag: "Montag", essen: "-"});
    }
    
    //MENÜ 3
    const [el3] = await page.$x('//*[@id="tab-mon"]/div/div[3]/small');

    if(el3 != null) {
        const menu3 = await el3.getProperty('textContent');
        const menuTxt3 = await menu3.jsonValue();

        obj.menu.push({tag: "Montag", essen: menuTxt3.replace(/([A-Z])/g, ' $1').trim()});
    } else {
        obj.menu.push({tag: "Montag", essen: "-"});
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////

    //Dienstag

    //MENÜ 1
    const [el4] = await page.$x('//*[@id="tab-tue"]/div/div[1]/small');

    if(el4 != null) {
        const menu4 = await el4.getProperty('textContent');
        const menuTxt4 = await menu4.jsonValue();

        obj.menu.push({tag: "Dienstag", essen: menuTxt4.replace(/([A-Z])/g, ' $1').trim()});
    } else {
        obj.menu.push({tag: "Dienstag", essen: "-"});
    }

    //MENÜ 2
    const [el5] = await page.$x('//*[@id="tab-tue"]/div/div[2]/small');

    if(el5 != null) {
        const menu5 = await el5.getProperty('textContent');
        const menuTxt5 = await menu5.jsonValue();

        obj.menu.push({tag: "Dienstag", essen: menuTxt5.replace(/([A-Z])/g, ' $1').trim()});
    } else {
        obj.menu.push({tag: "Dienstag", essen: "-"});
    }
    
    //MENÜ 3
    const [el6] = await page.$x('//*[@id="tab-tue"]/div/div[3]/small');

    if(el6 != null) {
        const menu6 = await el6.getProperty('textContent');
        const menuTxt6 = await menu6.jsonValue();

        obj.menu.push({tag: "Dienstag", essen: menuTxt6.replace(/([A-Z])/g, ' $1').trim()});
    } else {
        obj.menu.push({tag: "Dienstag", essen: "-"});
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////

    //MITTWOCH

    //MENÜ 1
    const [el7] = await page.$x('//*[@id="tab-wed"]/div/div[1]/small');

    if(el7 != null) {
        const menu7 = await el7.getProperty('textContent');
        const menuTxt7 = await menu7.jsonValue();

        obj.menu.push({tag: "Mittwoch", essen: menuTxt7.replace(/([A-Z])/g, ' $1').trim()});
    } else {
        obj.menu.push({tag: "Mittwoch", essen: "-"});
    }

    //MENÜ 2
    const [el8] = await page.$x('//*[@id="tab-wed"]/div/div[2]/small');

    if(el8 != null) {
        const menu8 = await el8.getProperty('textContent');
        const menuTxt8 = await menu8.jsonValue();

        obj.menu.push({tag: "Mittwoch", essen: menuTxt8.replace(/([A-Z])/g, ' $1').trim()});
    } else {
        obj.menu.push({tag: "Mittwoch", essen: "-"});
    }
    
    //MENÜ 3
    const [el9] = await page.$x('//*[@id="tab-wed"]/div/div[3]/small');

    if(el9 != null) {
        const menu9 = await el9.getProperty('textContent');
        const menuTxt9 = await menu9.jsonValue();

        obj.menu.push({tag: "Mittwoch", essen: menuTxt9.replace(/([A-Z])/g, ' $1').trim()});
    } else {
        obj.menu.push({tag: "Mittwoch", essen: "-"});
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    
    //DONNERSTAG

    //MENÜ 1
    const [el10] = await page.$x('//*[@id="tab-thu"]/div/div[1]/small');

    if(el10 != null) {
        const menu10 = await el10.getProperty('textContent');
        const menuTxt10 = await menu10.jsonValue();

        obj.menu.push({tag: "Donnerstag", essen: menuTxt10.replace(/([A-Z])/g, ' $1').trim()});
    } else {
        obj.menu.push({tag: "Donnerstag", essen: "-"});
    }

    //MENÜ 2
    const [el11] = await page.$x('//*[@id="tab-thu"]/div/div[2]/small');

    if(el11 != null) {
        const menu11 = await el11.getProperty('textContent');
        const menuTxt11 = await menu11.jsonValue();

        obj.menu.push({tag: "Donnerstag", essen: menuTxt11.replace(/([A-Z])/g, ' $1').trim()});
    } else {
        obj.menu.push({tag: "Donnerstag", essen: "-"});
    }
    
    //MENÜ 3
    const [el12] = await page.$x('//*[@id="tab-thu"]/div/div[3]/small');

    if(el12 != null) {
        const menu12 = await el12.getProperty('textContent');
        const menuTxt12 = await menu12.jsonValue();

        obj.menu.push({tag: "Donnerstag", essen: menuTxt12.replace(/([A-Z])/g, ' $1').trim()});
    } else {
        obj.menu.push({tag: "Donnerstag", essen: "-"});
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////

        //FREITAG

        //MENÜ 1
        const [el13] = await page.$x('//*[@id="tab-fri"]/div/div[1]/small');

        if(el13 != null) {
            const menu13 = await el13.getProperty('textContent');
            const menuTxt13 = await menu13.jsonValue();
    
            obj.menu.push({tag: "Freitag", essen: menuTxt13.replace(/([A-Z])/g, ' $1').trim()});
        } else {
            obj.menu.push({tag: "Freitag", essen: "-"});
        }
    
        //MENÜ 2
        const [el14] = await page.$x('//*[@id="tab-fri"]/div/div[2]/small');
    
        if(el14 != null) {
            const menu14 = await el14.getProperty('textContent');
            const menuTxt14 = await menu14.jsonValue();
    
            obj.menu.push({tag: "Freitag", essen: menuTxt14.replace(/([A-Z])/g, ' $1').trim()});
        } else {
            obj.menu.push({tag: "Freitag", essen: "-"});
        }
        
        //MENÜ 3
        const [el15] = await page.$x('//*[@id="tab-fri"]/div/div[3]/small');
    
        if(el15 != null) {
            const menu15 = await el15.getProperty('textContent');
            const menuTxt15 = await menu15.jsonValue();
    
            obj.menu.push({tag: "Freitag", essen: menuTxt15.replace(/([A-Z])/g, ' $1').trim()});
        } else {
            obj.menu.push({tag: "Freitag", essen: "-"});
        }
    
        ///////////////////////////////////////////////////////////////////////////////////////////////////

    var json = JSON.stringify(obj, null, 2);

    fs.writeFile('../menu.json', json, function(err, result) {
        if(err) {
            console.log('error', err);
        }
    });

    browser.close();
}

scrapeProduct("https://www.swfr.de/essen/mensen-cafes-speiseplaene/mensa-gengenbach");