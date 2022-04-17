import React from "react";
import Header from "./Header";
import Content from "./Content";
import Total from "./Total";

const Course = ({courses}) => {
    return (
        <div>
            {courses.map(x =>
                <div key={x.id}>
                    <Header course={x.name}/>
                    <Content course={x}/>
                    <Total course={x}/>
                </div>
            )}
        </div>
    )
}

export default Course
