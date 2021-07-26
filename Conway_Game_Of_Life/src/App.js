import React, {useState} from 'react'
import Cell from "./Cell";
import "./Row.css";
import useInterval from "./useInterval";


function generateMatrix(x, y) {
    return new Array(x).fill(0).map(() => new Array(y).fill(0))
}

function randomInt() {
    return Math.floor(Math.random() * 2)
}

const Button = (props) => {
    return (
        <button onClick={props.handleClick}>
            {props.text}
        </button>
    )
}


const App = () => {

        const [grid, setGrid] = useState(generateMatrix(10, 10))

        const invertValue = (i, j) => {
            const copy = [...grid]
            if (copy[i][j] === 0) {
                copy[i][j] = 1
            } else {
                copy[i][j] = 0
            }
            setGrid(copy)
        }

        const reset = () => {
            setGrid(generateMatrix(10, 10))
        }

        const tick = () => {
            const newgrid = generateMatrix(10, 10)
            for (let i = 0; i < 10; i++) {
                for (let j = 0; j < 10; j++) {
                    let counter = 0
                    if (grid[i][j] === 0) {
                        if (i === 0 && j === 0) {
                            if (grid[i][j + 1] === 1)
                                counter++
                            if (grid[i + 1][j + 1] === 1)
                                counter++
                            if (grid[i + 1][j] === 1)
                                counter++
                        } else if (i === 0 && j === 9) {
                            if (grid[i][j - 1] === 1)
                                counter++
                            if (grid[i + 1][j] === 1)
                                counter++
                            if (grid[i + 1][j - 1] === 1)
                                counter++
                        } else if (i === 9 && j === 0) {
                            if (grid[i][j + 1] === 1)
                                counter++
                            if (grid[i - 1][j] === 1)
                                counter++
                            if (grid[i - 1][j + 1] === 1)
                                counter++
                        } else if (i === 9 && j === 9) {
                            if (grid[i][j - 1] === 1)
                                counter++
                            if (grid[i - 1][j] === 1)
                                counter++
                            if (grid[i - 1][j - 1] === 1)
                                counter++
                        } else if (i === 0) {
                            if (grid[i][j - 1] === 1)
                                counter++
                            if (grid[i][j + 1] === 1)
                                counter++
                            if (grid[i + 1][j - 1] === 1)
                                counter++
                            if (grid[i + 1][j] === 1)
                                counter++
                            if (grid[i + 1][j + 1] === 1)
                                counter++
                        } else if (i === 9) {
                            if (grid[i][j - 1] === 1)
                                counter++
                            if (grid[i - 1][j - 1] === 1)
                                counter++
                            if (grid[i - 1][j] === 1)
                                counter++
                            if (grid[i - 1][j + 1] === 1)
                                counter++
                            if (grid[i][j + 1] === 1)
                                counter++
                        } else if (j === 0) {
                            if (grid[i - 1][j] === 1)
                                counter++
                            if (grid[i - 1][j + 1] === 1)
                                counter++
                            if (grid[i][j + 1] === 1)
                                counter++
                            if (grid[i + 1][j + 1] === 1)
                                counter++
                            if (grid[i + 1][j] === 1)
                                counter++
                        } else if (j === 9) {
                            if (grid[i - 1][j] === 1)
                                counter++
                            if (grid[i - 1][j - 1] === 1)
                                counter++
                            if (grid[i][j - 1] === 1)
                                counter++
                            if (grid[i + 1][j - 1] === 1)
                                counter++
                            if (grid[i + 1][j] === 1)
                                counter++
                        } else {
                            if (grid[i - 1][j - 1] === 1)
                                counter++
                            if (grid[i - 1][j] === 1)
                                counter++
                            if (grid[i - 1][j + 1] === 1)
                                counter++
                            if (grid[i][j + 1] === 1)
                                counter++
                            if (grid[i + 1][j + 1] === 1)
                                counter++
                            if (grid[i + 1][j] === 1)
                                counter++
                            if (grid[i + 1][j - 1] === 1)
                                counter++
                            if (grid[i][j - 1] === 1)
                                counter++
                        }
                        if (counter === 3)
                            newgrid[i][j] = 1
                    } else {
                        if (i === 0 && j === 0) {
                            if (grid[i][j + 1] === 1)
                                counter++
                            if (grid[i + 1][j + 1] === 1)
                                counter++
                            if (grid[i + 1][j] === 1)
                                counter++
                        } else if (i === 0 && j === 9) {
                            if (grid[i][j - 1] === 1)
                                counter++
                            if (grid[i + 1][j] === 1)
                                counter++
                            if (grid[i + 1][j - 1] === 1)
                                counter++
                        } else if (i === 9 && j === 0) {
                            if (grid[i][j + 1] === 1)
                                counter++
                            if (grid[i - 1][j] === 1)
                                counter++
                            if (grid[i - 1][j + 1] === 1)
                                counter++
                        } else if (i === 9 && j === 9) {
                            if (grid[i][j - 1] === 1)
                                counter++
                            if (grid[i - 1][j] === 1)
                                counter++
                            if (grid[i - 1][j - 1] === 1)
                                counter++
                        } else if (i === 0) {
                            if (grid[i][j - 1] === 1)
                                counter++
                            if (grid[i][j + 1] === 1)
                                counter++
                            if (grid[i + 1][j - 1] === 1)
                                counter++
                            if (grid[i + 1][j] === 1)
                                counter++
                            if (grid[i + 1][j + 1] === 1)
                                counter++
                        } else if (i === 9) {
                            if (grid[i][j - 1] === 1)
                                counter++
                            if (grid[i - 1][j - 1] === 1)
                                counter++
                            if (grid[i - 1][j] === 1)
                                counter++
                            if (grid[i - 1][j + 1] === 1)
                                counter++
                            if (grid[i][j + 1] === 1)
                                counter++
                        } else if (j === 0) {
                            if (grid[i - 1][j] === 1)
                                counter++
                            if (grid[i - 1][j + 1] === 1)
                                counter++
                            if (grid[i][j + 1] === 1)
                                counter++
                            if (grid[i + 1][j + 1] === 1)
                                counter++
                            if (grid[i + 1][j] === 1)
                                counter++
                        } else if (j === 9) {
                            if (grid[i - 1][j] === 1)
                                counter++
                            if (grid[i - 1][j - 1] === 1)
                                counter++
                            if (grid[i][j - 1] === 1)
                                counter++
                            if (grid[i + 1][j - 1] === 1)
                                counter++
                            if (grid[i + 1][j] === 1)
                                counter++
                        } else {
                            if (grid[i - 1][j - 1] === 1)
                                counter++
                            if (grid[i - 1][j] === 1)
                                counter++
                            if (grid[i - 1][j + 1] === 1)
                                counter++
                            if (grid[i][j + 1] === 1)
                                counter++
                            if (grid[i + 1][j + 1] === 1)
                                counter++
                            if (grid[i + 1][j] === 1)
                                counter++
                            if (grid[i + 1][j - 1] === 1)
                                counter++
                            if (grid[i][j - 1] === 1)
                                counter++
                        }
                        if (counter === 2 || counter === 3)
                            newgrid[i][j] = 1
                        else
                            newgrid[i][j] = 0
                    }
                    console.log(counter)
                }
            }
            setGrid(newgrid)
        }
        useInterval(tick, 2000)


        console.log(grid.length)
        return (
            <>
                <div className="grid">
                    {grid.map((row, i) => (
                        <div className="row" key={i}>
                            {row.map((col, j) => (
                                <Cell value={grid[i][j]} setValue={() => invertValue(i, j)} key={j}>{col}</Cell>
                            ))}
                        </div>
                    ))}
                </div>
                <Button handleClick={tick} text='Next generation'/>
                <Button handleClick={reset} text='Reset'/>
            </>

        )
    }
;

export default App