import React from "react";

const Total = ({course}) => {
    return (
        <div>
            <p>
                <b>total of exercises {course.parts.reduce((s,p) => {
                    return s + p.exercises},0)}
                </b>
            </p>
        </div>
    )
}

export default Total