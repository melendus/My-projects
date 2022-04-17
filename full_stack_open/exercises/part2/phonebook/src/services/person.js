import axios from 'axios'

const baseUrl = 'http://localhost:3001/api/persons'

const getAll = () => {
    const request = axios.get(baseUrl)
    return request.then(response => response.data)
}

const postPerson = personObject => {
    const request = axios.post(baseUrl,personObject)
    return request.then(response => response.data)
}

const deletePerson = personID => {
    const newURL = `${baseUrl}/${personID}`
    return axios.delete(newURL)
}

const putPerson = (personID,newObject) => {
    const request = axios.put(`${baseUrl}/${personID}`,newObject)
    return request.then(response => response.data)
}

export default {getAll,postPerson,deletePerson,putPerson}