// import * as $ from 'jquery'

export default {
    getProducts(success) {
        // $.get('https://jsonplaceholder.typicode.com/todos').then(
        //     (response) => {
        //         success(response)
        //     },
        //     (response) => {
        //         error(response)
        //     }
        // )
        var response = {
       "type" : "categoryHierarchyWsDTO",
       "id" : "biscuits",
       "lastModified" : "2022-08-23T02:22:21+0000",
       "url" : "/catalogsmealeculeProductCatalog/Online/biscuits?fields=FULL",
       "currentPage" : 0,
       "numberOfPages" : 1,
       "pageSize" : 10,
       "products" : [ {
          "code" : "503",
          "mealeculeQuotientData" : {
             "carbohydrate" : 67.0,
             "code" : "503-mq",
             "energy" : 512.0,
             "fat" : 24.0,
             "protein" : 7.0,
             "sugar" : 22.0,
             "weightInG" : 100.0
          },
          "name" : "Britannia Good Day Cashew Cookies",
          "price" : {
             "currencyIso" : "USD",
             "value" : 25.0
          },
          "purchasable" : true,
          "url" : "/mealecule/products/503"
       }, {
          "code" : "504",
          "mealeculeQuotientData" : {
             "carbohydrate" : 70.0,
             "code" : "504-mq",
             "energy" : 488.0,
             "fat" : 20.0,
             "fiber" : 6.0,
             "protein" : 7.0,
             "sugar" : 20.5,
             "weightInG" : 100.0
          },
          "name" : "Britannia NutriChoice 5 Grain Digestive, Healthy Snack",
          "price" : {
             "currencyIso" : "USD",
             "value" : 75.0
          },
          "purchasable" : true,
          "url" : "/mealecule/products/504"
       }, {
          "code" : "502",
          "mealeculeQuotientData" : {
             "carbohydrate" : 68.0,
             "code" : "502-mq",
             "energy" : 493.0,
             "fat" : 21.0,
             "fiber" : 6.0,
             "protein" : 8.0,
             "sugar" : 14.5,
             "weightInG" : 100.0
          },
          "name" : "Britannia NutriChoice Digestive High Fibre Biscuits 50 g Multipack",
          "price" : {
             "currencyIso" : "USD",
             "value" : 112.0
          },
          "purchasable" : true,
          "url" : "/mealecule/products/502"
       }, {
          "code" : "501",
          "mealeculeQuotientData" : {
             "carbohydrate" : 71.4,
             "code" : "501-mq",
             "energy" : 483.0,
             "fat" : 19.7,
             "protein" : 5.3,
             "sugar" : 38.0,
             "weightInG" : 100.0
          },
          "name" : "Cadbury Oreo Creame Biscuit - Chocolate, 46.3 g",
          "price" : {
             "currencyIso" : "USD",
             "value" : 10.0
          },
          "purchasable" : true,
          "url" : "/mealecule/products/501"
       }, {
          "code" : "506",
          "mealeculeQuotientData" : {
             "carbohydrate" : 64.8,
             "code" : "506-mq",
             "energy" : 419.0,
             "fat" : 13.2,
             "protein" : 10.2,
             "sugar" : 27.2,
             "weightInG" : 100.0
          },
          "name" : "Fresho Signature Cookies - Honey & Oats, Chewy",
          "price" : {
             "currencyIso" : "USD",
             "value" : 149.0
          },
          "purchasable" : true,
          "url" : "/mealecule/products/506"
       }, {
          "code" : "505",
          "mealeculeQuotientData" : {
             "carbohydrate" : 61.0,
             "energy" : 520.0,
             "fat" : 27.2,
             "protein" : 7.3,
             "sugar" : 35.8,
             "weightInG" : 100.0
          },
          "name" : "Sunfeast Dark Fantasy - Choco Fills, Original Filled Cookies",
          "price" : {
             "currencyIso" : "USD",
             "value" : 40.0
          },
          "purchasable" : true,
          "url" : "/mealecule/products/505"
       } ],
       "subcategories" : [ ],
       "totalNumber" : 6
        }
        success(response);
    },

    makeMQData (mq) {
        let cal = mq.energy;
        let weight = mq.weightInG || mq.weightInML;
        if (mq.code) delete mq['code']
        let mqArray = [];
        for (let k in mq) {
            if (['energy','weightInG','code'].indexOf(k) >-1) continue;
            let v = mq[k];
            console.log(k)
            mqArray.push({key:k, value:v})
        } 
        return {
            cal,
            weight,
            chart: mqArray
        }
    }
}

