import React, {useState, useEffect} from "react";
import axios from "axios";
import SingleCountry from "./SingleCountry";

const Filter = ({countries, countryToBeFound,api_key,api,capital,setCapital,setCountryToBeFound}) => {
    const result = countries.filter(x => {
        return x.name.toLowerCase().includes(countryToBeFound.toLowerCase());
    })
    if (countryToBeFound === '') {
        return (
            <></>
        )
    } else if (result.length > 10) {
        return (
            <p>Too many matches,specify another filter</p>
        )
    } else if (result.length === 1) {
        return (
            <SingleCountry api_key={api_key} api={api} result={result}
                           capital={capital} setCapital={setCapital}/>
        )
    } else {
        return (
            <div>
                {result.map(y =>
                    <p key={y.name}>{y.name}
                        <button onClick={() => setCountryToBeFound(y.name)}>show</button>
                    </p>
                )}
            </div>
        )
    }
}

const App = () => {

    const [countries, setCountries] = useState([])
    const [countryToBeFound, setCountryToBeFound] = useState('')
    const [capital, setCapital] = useState('')
    const api = 'http://api.weatherstack.com/current'
    const api_key = process.env.REACT_APP_API_KEY

    useEffect(() => {
        axios
            .get('https://restcountries.eu/rest/v2/all')
            .then(response => {
                setCountries(response.data)
            })
    }, [])


    const handleCountryToBeFound = (event) => {
        setCountryToBeFound(event.target.value)
    }

    return (
        <div>
            <form>
                <div>
                    find countries
                    <input
                        value={countryToBeFound}
                        onChange={handleCountryToBeFound}
                    />
                </div>
            </form>
            <Filter countries={countries} countryToBeFound={countryToBeFound} setCapital={setCapital} capital={capital} api={api} api_key={api_key} setCountryToBeFound={setCountryToBeFound}/>
        </div>
    )
}

export default App;
