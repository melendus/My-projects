const blogRouter = require('express').Router()
const Blog = require('../models/blog')
const User = require('../models/user')
const jwt = require('jsonwebtoken')

const getTokenFrom = request => {
    const authorization = request.get('authorization')
    if(authorization && authorization.toLowerCase().startsWith('bearer ')) {
        return authorization.substring(7)
    }
    return null
}

blogRouter.post('/', async (request, response) => {
    const blog = request.body
    const token = getTokenFrom(request)
    const decodedToken = jwt.verify(token, process.env.SECRET)
    if (!token || !decodedToken.id) {
        return response.status(401).json({error: 'token missing or invalid'})
    }
    const user = await User.findById(decodedToken.id)
    console.log(user)

    if (!blog.likes) {
        blog.likes = 0
    } else if (!blog.title && !blog.url) {
        return response.status(400).json({
            error: 'Bad request'
        })
    }

    const newBlog = new Blog({
        title: blog.title,
        author: blog.author,
        url: blog.url,
        likes: blog.likes,
        user: user._id
    })
    const savedBlog = await newBlog.save()
    user.blogs = user.blogs.concat(savedBlog._id)
    await user.save()
    response.json(savedBlog)

    /*
        newBlog
            .save()
            .then(result => {
                response.status(201).json(result)
            })*/
})

blogRouter.delete('/:id', async (request, response) => {
    await Blog.findByIdAndRemove(request.params.id)
    response.status(204).end()
})

blogRouter.put('/:id', async (request, response) => {
    const body = request.body
    const blog = {
        likes: body.likes
    }
    Blog.findByIdAndUpdate(request.params.id, blog, {new: true})
        .then(updatedBlog => {
            response.json(updatedBlog.toJSON())
        })
})

blogRouter.get('/', async (request, response) => {
    const blogs = await Blog.find({}).populate('user', {username: 1, name: 1})

    response.json(blogs)
})

module.exports = blogRouter