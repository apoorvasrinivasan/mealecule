// import * as $ from 'jquery'

export default {
    getProducts(cat, success) {
        console.log(cat)
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

    getProduct(code, success) {
        alert(code)
        var response = {
           "availableForPickup" : false,
           "baseOptions" : [ ],
           "categories" : [ {
              "code" : "biscuits"
           } ],
           "mealeculeQuotientData" : {
             "carbohydrate" : 67.0,
             "energy" : 512.0,
             "fat" : 24.0,
             "protein" : 7.0,
             "sugar" : 22.0,
             "weightInG" : 100.0
          },

           "code" : "505",
           "manufacturer" : "Sunfeast",
           "name" : "Sunfeast Dark Fantasy - Choco Fills, Original Filled Cookies",
           "numberOfReviews" : 0,
           "price" : {
              "currencyIso" : "USD",
              "formattedValue" : "$40.00",
              "priceType" : "BUY",
              "value" : 40.0
           },
           "priceRange" : {
           },
           "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Rhoncus urna neque viverra justo nec ultrices dui sapien. Aliquet lectus proin nibh nisl condimentum id. Tristique senectus et netus et malesuada fames ac turpis. Neque ornare aenean euismod elementum nisi quis. Elit sed vulputate mi sit amet mauris.",
            
           "purchasable" : true,
           "stock" : {
              "stockLevel" : 1000,
              "stockLevelStatus" : "inStock"
           },
           "summary" : "A cookie for the purely indulgent, Sunfeast Dark Fantasy Choco Fills is your companion for the moments when you #CantWaitWontWait.",
           "url" : "/products/505"
        }
        success(response);
       
    } ,
    getCategories(success, error){
        var response = {
           "type" : "catalogVersionWsDTO",
           "id" : "Online",
           "url" : "/mealeculeProductCatalog/Online",
           "categories" : [ {
              "id" : "dessert",
              "url" : "/catalogs/mealeculeProductCatalog/Online/categories/dessert?fields=FULL",
              "subcategories" : [ {
                 "id" : "iceCream",
                 "url" : "/catalogs/mealeculeProductCatalog/Online/categories/iceCream?fields=FULL",
                 "subcategories" : [ ]
              } ]
           }, {
              "id" : "beverages",
              "url" : "/catalogs/mealeculeProductCatalog/Online/categories/beverages?fields=FULL",
              "subcategories" : [ {
                 "id" : "cereals",
                 "url" : "/catalogs/mealeculeProductCatalog/Online/categories/cereals?fields=FULL",
                 "subcategories" : [ ]
              }, {
                 "id" : "healthDrinks",
                 "url" : "/catalogs/mealeculeProductCatalog/Online/categories/healthDrinks?fields=FULL",
                 "subcategories" : [ ]
              }, {
                 "id" : "coffee",
                 "url" : "/catalogs/mealeculeProductCatalog/Online/categories/coffee?fields=FULL",
                 "subcategories" : [ ]
              }, {
                 "id" : "softDrinks",
                 "url" : "/catalogs/mealeculeProductCatalog/Online/categories/softDrinks?fields=FULL",
                 "subcategories" : [ ]
              }, {
                 "id" : "juices",
                 "url" : "/catalogs/mealeculeProductCatalog/Online/categories/juices?fields=FULL",
                 "subcategories" : [ ]
              } ]
           }, {
              "id" : "baking",
              "url" : "/catalogs/mealeculeProductCatalog/Online/categories/baking?fields=FULL",
              "subcategories" : [ {
                 "id" : "cookie",
                 "url" : "/catalogs/mealeculeProductCatalog/Online/categories/cookie?fields=FULL",
                 "subcategories" : [ ]
              }, {
                 "id" : "cakes",
                 "url" : "/catalogs/mealeculeProductCatalog/Online/categories/cakes?fields=FULL",
                 "subcategories" : [ ]
              }, {
                 "id" : "bread",
                 "url" : "/catalogs/mealeculeProductCatalog/Online/categories/bread?fields=FULL",
                 "subcategories" : [ ]
              } ]
           }, {
              "id" : "snacks",
              "url" : "/catalogs/mealeculeProductCatalog/Online/categories/snacks?fields=FULL",
              "subcategories" : [ {
                 "id" : "noodles",
                 "url" : "/catalogs/mealeculeProductCatalog/Online/categories/noodles?fields=FULL",
                 "subcategories" : [ ]
              }, {
                 "id" : "biscuits",
                 "url" : "/catalogs/mealeculeProductCatalog/Online/categories/biscuits?fields=FULL",
                 "subcategories" : [ ]
              }, {
                 "id" : "pasta",
                 "url" : "/catalogs/mealeculeProductCatalog/Online/categories/pasta?fields=FULL",
                 "subcategories" : [ ]
              } ]
           }, {
              "id" : "diary",
              "url" : "/catalogs/mealeculeProductCatalog/Online/categories/diary?fields=FULL",
              "subcategories" : [ {
                 "id" : "butter",
                 "url" : "/catalogs/mealeculeProductCatalog/Online/categories/butter?fields=FULL",
                 "subcategories" : [ ]
              }, {
                 "id" : "cheese",
                 "url" : "/catalogs/mealeculeProductCatalog/Online/categories/cheese?fields=FULL",
                 "subcategories" : [ ]
              }, {
                 "id" : "milk",
                 "url" : "/catalogs/mealeculeProductCatalog/Online/categories/milk?fields=FULL",
                 "subcategories" : [ ]
              } ]
           } ]
        };
        success(response);
        error(response);
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

