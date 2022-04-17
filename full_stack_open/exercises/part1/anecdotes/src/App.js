import React, {useState} from 'react'


const Button = (props) => {
    return (
        <button onClick={props.handleClick}>
            {props.text}
        </button>
    )
}

/*const HighestVotes = (array) => {
    let max = 0
    let k = 0
    for (let i = 0; i < array.length; i++) {
        if (array[i] > max) {
            max = array[i]
            k = i
        }
    }
    console.log(k)
    return k
}*/


const App = () => {
    const anecdotes = [
        'If it hurts, do it more often',
        'Adding manpower to a late software project makes it later!',
        'The first 90 percent of the code accounts for the first 90 percent of the development time...The remaining 10 percent of the code accounts for the other 90 percent of the development time.',
        'Any fool can write code that a computer can understand. Good programmers write code that humans can understand.',
        'Premature optimization is the root of all evil.',
        'Debugging is twice as hard as writing the code in the first place. Therefore, if you write the code as cleverly as possible, you are, by definition, not smart enough to debug it.',
        'Programming without an extremely heavy use of console.log is same as if a doctor would refuse to use x-rays or blod tests when dianosing patients'
    ]
    const [selected, setSelected] = useState(0)
    //console.log(copy[2])
    //let maximum = 0
    const [array, setArray] = useState(new Array(anecdotes.length).fill(0))
    /*   const increaseByOne = (props) => {
           array[props.selected]++
       }*/
    const increaseByOne = (selected) => {
        const copy = [...array]
        copy[selected] += 1
        setArray(copy)
    }

    const HighestVotes = () => {
        let max = 0
        let k = 0
        for (let i = 0; i < array.length; i++) {
            console.log(array[i])
            if (array[i] > max) {
                max = array[i]
                k = i
                console.log(k)
            }
        }
        return k
    }

    return (

        <div>
            <p>{anecdotes[selected]}</p>
            <p>has {array[selected]} votes</p>

            <p>
                <Button handleClick={() => increaseByOne(selected)} text='vote'/>
                <Button handleClick={() => setSelected(Math.floor(Math.random() * anecdotes.length))}
                        text='next anecdote'/>
            </p>
            <p>{anecdotes[HighestVotes()]}</p>
        </div>
    )
}

export default App