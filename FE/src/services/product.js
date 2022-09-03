import * as $ from 'jquery'
import User from './user'

let BASE_URL = "/api/mealeculecommercewebservices/v2/mealecule";
User.setHeader();

export default {
    
    getProducts(cat, success, error) {
        $.get(
         BASE_URL+'/catalogs/mealeculeProductCatalog/Online/categories/'+cat+'?fields=FULL'
         ).then(
            (response) => {
                success(response)
            },
            (response) => {
                error(response)
            }
        )
    },

    getProduct(code, success, error) {
        $.get(BASE_URL+'/products/'+code).then(
            (response) => {
                success(response)
            },
            (response) => {
                error(response);
            })
        
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
        let cal = (mq)? mq.energy : 0;
        let weight = mq.weightInG || mq.weightInML;
        if (mq.code) delete mq['code']
        let mqArray = [];
        for (let k in mq) {
            if (['energy','weightInG','code'].indexOf(k) >-1) continue;
            let v = mq[k];
            // console.log(k)
            mqArray.push({key:k, value:v})
        } 
        return {
            cal,
            weight,
            chart: mqArray
        }
    }
}

