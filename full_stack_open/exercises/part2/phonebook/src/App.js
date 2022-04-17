import React, {useState, useEffect} from 'react'
import Filter from "./Filter";
import AddPerson from "./AddPerson";
import FilterForm from "./FilterForm";
import personServices from "./services/person"
import './index.css'
import Notification from "./notification";

const App = () => {
    const [persons, setPersons] = useState([])
    const [newName, setNewName] = useState('')
    const [newNumber, setNewNumber] = useState('')
    const [personToBeFound, setPersonToBeFound] = useState('')
    const [errorMessage, setErrorMessage] = useState('')

    useEffect(() => {
        console.log('efect')
        personServices
            .getAll()
            .then(allPersons => {
                console.log('promise fulfilled')
                setPersons(allPersons)
            })
    }, [])
    const addPerson = (event) => {
        let id
        event.preventDefault()
        const personObject = {
            name: newName,
            number: newNumber
        }
        let ok = true
        persons.forEach(x => {
                if (x.name === newName) {
                    ok = false
                    id = x.id
                }
            }
        )
        if (ok === false) {
            if (window.confirm(`${newName} is already added to phonebook, replace the old number with a new one?`)) {
                personServices
                    .putPerson(id, personObject)
                    .then(returnedPerson => {
                        setPersons(persons.map(person => person.id !== id ? person : returnedPerson))
                        setErrorMessage(`${personObject.name} was modified`)
                        setTimeout(() => {
                            setErrorMessage('')
                        }, 5000)
                    })
            }
            console.log(persons)
        } else {
            personServices
                .postPerson(personObject)
                .then(newPersons => {
                        setPersons(persons.concat(newPersons))
                        setNewName('')
                        setNewNumber('')
                        setErrorMessage(`${personObject.name} was added`)
                        setTimeout(() => {
                            setErrorMessage('')
                        }, 5000)
                    }
                )
                .catch(error => {
                    console.log(error.response.data)
                    setErrorMessage(error.response.data.error)
                    setTimeout(() => {
                        setErrorMessage('')
                    }, 5000)
                })
        }
    }

    const deletePerson = personToBeDeleted => {
        if (window.confirm(`Do you want to delete ${personToBeDeleted.name}?`)) {
            personServices
                .deletePerson(personToBeDeleted.id)
                .then(() => {
                    setPersons(persons.filter(x => x.id !== personToBeDeleted.id))
                    setErrorMessage(`${personToBeDeleted.name} was deleted`)
                    setTimeout(() => {
                        setErrorMessage('')
                    }, 5000)
                })
                .catch(() => {
                    setErrorMessage(`Information of ${personToBeDeleted.name} has already been deleted from server`)
                    setTimeout( () => {
                        setErrorMessage('')
                    },5000)
                })
        }
    }

    const handleNameChange = (event) => {
        console.log(event.target.value)
        setNewName(event.target.value)
    }

    const handleNumberChange = (event) => {
        console.log(event.target.value)
        setNewNumber(event.target.value)
    }

    const handlePersonToBeFound = (event) => {
        setPersonToBeFound(event.target.value)
    }


    return (
        <div>
            <h2>Phonebook</h2>
            <Notification message={errorMessage}/>
            <FilterForm personToBeFound={personToBeFound} handlePersonToBeFound={handlePersonToBeFound}/>
            <AddPerson addPerson={addPerson} handleNumberChange={handleNumberChange} handleNameChange={handleNameChange}
                       newName={newName} newNumber={newNumber}/>

            <h2>Numbers</h2>
            {persons.map(x =>
                <Filter personToBeFound={personToBeFound} x={x} key={x.name} deletePerson={deletePerson}/>
            )}
        </div>
    )
}

export default App