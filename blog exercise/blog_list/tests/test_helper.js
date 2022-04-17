const Blog = require('../models/blog')
const User = require('../models/user')

const initialBlogs = [
    {
        content: 'HTML is easy',
        date: new Date(),
        important: false
    },
    {
        content: 'Browser can execute only Javascript',
        date: new Date(),
        important: true
    }
]

const nonExistingId = async () => {
    const Blog = new Blog({ content: 'willremovethissoon', date: new Date() })
    await Blog.save()
    await Blog.remove()

    return Blog._id.toString()
}

const BlogsInDb = async () => {
    const Blogs = await Blog.find({})
    return Blogs.map(Blog => Blog.toJSON())
}

const usersInDb = async () => {
    const users = await User.find({})
    return users.map(u => u.toJSON())
}

module.exports = {
    initialBlogs,
    nonExistingId,
    BlogsInDb,
    usersInDb,
}