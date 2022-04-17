import React from "react";

const Filter = ({x, personToBeFound,deletePerson}) => {
    if (personToBeFound === '') {
        return (
            <p>{x.name} {x.number} <button onClick={() => {deletePerson(x)}}>delete</button></p>
        )
    } else {
        if (x.name.toLowerCase().includes(personToBeFound)) {
            return (
                <p>{x.name} {x.number}<button onClick={() => {deletePerson(x)}}>delete</button></p>
            )
        } else {
            return (
                <></>
            )
        }
    }
}

export default Filter