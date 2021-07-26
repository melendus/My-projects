import React from "react";
import "./Cell.css";


const Cell = ({value, setValue}) => {
    const classes = `cell ${value === 1 ? "black" : "white"}`

    console.log(classes)

    return (
        <div onClick={setValue} className={classes}>
        </div>
    )
}

export default Cell;