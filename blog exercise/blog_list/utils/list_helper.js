const dummy = (blogs) => {
    return 1
}

const totalLikes = (blogs) => {
    const reducer = (sum,item) => {
        return sum + item.likes
    }

    return blogs.length === 0
        ? 0
        : blogs.reduce(reducer,0)
}

const favouriteBlog = (blogs) => {
    const reducer = (favObject,item) => {
        if (item.likes > favObject.likes)
            return item
        else return favObject
    }

    return blogs.reduce(reducer,{likes:0})
}

module.exports = {
    dummy,totalLikes,favouriteBlog
}