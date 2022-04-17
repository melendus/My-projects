import Part from "./Part";
import React from "react";

const Content = ({course}) => {
    return (
        <div>
            {course.parts.map(x =>
                <Part name={x.name} exercises={x.exercises} key={x.id}/>
            )}
        </div>
    )
}

export default Content