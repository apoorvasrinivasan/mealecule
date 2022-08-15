import * as $ from 'jquery'

export default {
    getProducts(success, error) {
        $.get('https://jsonplaceholder.typicode.com/todos').then(
            (response) => {
                success(response)
            },
            (response) => {
                error(response)
            }
        )
    }
}