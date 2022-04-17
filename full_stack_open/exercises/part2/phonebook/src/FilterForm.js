import React from "react";

const FilterForm = ({personToBeFound,handlePersonToBeFound}) => {
    return (
        <form>
            <div>
                filter shown with
                <input value={personToBeFound}
                       onChange={handlePersonToBeFound}
                />
            </div>
        </form>
    )
}
export default FilterForm