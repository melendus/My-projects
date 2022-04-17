import React, {useState} from 'react'

const App = () => {
    // save clicks of each button to its own state

    const [good, setGood] = useState(0)
    const [neutral, setNeutral] = useState(0)
    const [bad, setBad] = useState(0)

    return (
        <div>
            <Title title='give feedback'/>
            <Button handleClick={() => setGood(good + 1)} text='good'/>
            <Button handleClick={() => setNeutral(neutral + 1)} text='neutral'/>
            <Button handleClick={() => setBad(bad + 1)} text='bad'/>
            <Title title="statistics"/>
            <Statistics good={good} bad={bad} neutral={neutral}/>
        </div>
    )
}

const Title = ({title}) => <div><h1>{title}</h1></div>

const Statistic = ({text, number}) => {
    return (
        <tr>
            <td>{text}</td>
            <td>{number}</td>
        </tr>
    )
}

const Button = (props) => {
    return (
        <button onClick={props.handleClick}>
            {props.text}
        </button>
    )
}

const Statistics = ({good, neutral, bad}) => {
    const percent = good / (good + neutral + bad)
    if (good === 0 && neutral === 0 && bad === 0) {
        return (
            <div>
                No feedback given
            </div>
        )
    }
    return (
        <div>
            <table>
                <thead/>
                <tbody>
                <Statistic text="good" number={good}/>
                <Statistic text="neutral" number={neutral}/>
                <Statistic text="bad" number={bad}/>
                <Statistic text='all' number={(good + neutral + bad)}/>
                <Statistic text='average' number={(good - bad) / (good + neutral + bad)}/>
                <Statistic text='positive' number={percent + '%'}/>
                </tbody>
                <tfoot/>
            </table>
        </div>
    )
}

export default App