import React,{useState,useEffect} from "react";
import axios from "axios";

const SingleCountry = ({result,api,api_key,capital}) => {
    const [weather, setWeather] = useState({
        current: {
            temperature: 0,
            weather_icons: "",
            weather_descriptions: "",
            wind_dir: "",
            wind_speed: 0,
        },
    })
    useEffect(() => {
        const endpoint = `${api}?access_key=${api_key}&query=${result[0].capital}`
        axios
            .get(endpoint)
            .then(response => {
                console.log(response)
                setWeather(response.data)
            })
    }, [])
    console.log(capital)
    return (
        <div>
            <h1>{result[0].name}</h1>
            <p>capital {result[0].capital}</p>
            <p>population {result[0].population}</p>
            <h2>languages</h2>
            <ul>
                {result[0].languages.map(z =>
                    <li key={z.name}>{z.name}</li>)}
            </ul>
            <p><img src={result[0].flag} width={125} height={125} alt={result[0].name}/></p>
            <h2>Weather in {result[0].capital}</h2>
            <p><b>temperature:</b> {weather.current.temperature} Celsius</p>
            <p><img src={weather.current.weather_icons} alt={weather.current.weather_descriptions}/></p>
            <p><b>wind</b> {weather.current.wind_speed} {weather.current.wind_dir}</p>
        </div>
    )
}
export default SingleCountry