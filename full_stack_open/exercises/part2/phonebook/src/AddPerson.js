import React from "react";

const AddPerson = ({newName,addPerson,handleNameChange,newNumber,handleNumberChange}) => {
    return (
        <form onSubmit={addPerson}>
            <div>
                name:
                <input
                    value={newName}
                    onChange={handleNameChange}
                />
            </div>
            <div>
                number
                <input
                    value={newNumber}
                    onChange={handleNumberChange}
                />
            </div>
            <div>
                <button type="submit">add</button>
            </div>
        </form>
    )
}
export default AddPerson
