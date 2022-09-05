import * as $ from 'jquery'
import User from './user'

let BASE_URL = "/api/mealeculecommercewebservices/v2/mealecule";
User.setHeader();

export default {
    
    getProducts(cat, success, error) {
        $.get(
         BASE_URL+'/catalogs/mealeculeProductCatalog/Online/categories/'+cat + '?fields=FULL'
         ).then(
            (response) => {
                success(response)
            },
            (response) => {
               User.accessHandler(response)
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
               User.accessHandler(response)
                error(response);
            })
        
    } ,
    getCategories(success, error){
        
      $.get(BASE_URL+'/catalogs/mealeculeProductCatalog/Online').then(
        (r) =>{ success(r)},
        (r) =>{ error(r)}
      )
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

