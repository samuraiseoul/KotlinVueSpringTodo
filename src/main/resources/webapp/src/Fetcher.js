const BASE_URL = 'http://127.0.0.1:8080/';

function fetchWrapper(path, method, data = null){
    return fetch(BASE_URL + path, {
        method: method,
        body: data
    });
}

function jsonFetchWrapper(path, method, data = null){
    return fetchWrapper(path, method, data).then(response => response.json());
}

export default {
    post: function(path, data = new FormData()){
        return jsonFetchWrapper(path, 'POST', data);
    },
    delete: function(path) { //delete requests can't have a body
        return fetchWrapper(path, 'DELETE');
    },
    get: function(path) { //get requests can't have a body
        return jsonFetchWrapper(path, 'GET');
    }
}