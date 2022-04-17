const listHelper = require('../utils/list_helper')
const totalLikes = require('../utils/list_helper').totalLikes
const favouriteBlog = require('../utils/list_helper').favouriteBlog
const supertest = require('supertest')
const mongoose = require('mongoose')
const app = require('../app')
const api = supertest(app)

const Blog = require('../models/blog')
const initialBlogs = [
    {
        id: 0,
        title: 'my favourite blog',
        author: 'mr big pp',
        url: 'idk',
        likes: 69
    }
]
beforeEach(async () => {
    await Blog.deleteMany({})
    let blogObject = new Blog(initialBlogs[0])
    await blogObject.save()
})

test('dummy returns one', () => {
    const blogs = []
    const result = listHelper.dummy(blogs)
    expect(result).toBe(1)
})

describe('total of likes', () => {
    test('when there is no blog', () => {
        expect(totalLikes([])).toBe(0)
    })
    const listWithOneBlog = [
        {
            _id: '5a422aa71b54a676234d17f8',
            title: 'Go To Statement Considered Harmful',
            author: 'Edsger W. Dijkstra',
            url: 'http://www.u.arizona.edu/~rubinson/copyright_violations/Go_To_Considered_Harmful.html',
            likes: 5,
            __v: 0
        }
    ]
    test('where there is a single blog', () => {
        expect(totalLikes(listWithOneBlog)).toBe(5)
    })
    const listWithMoreBlogs = [
        {
            _id: '5a422aa71b54a676234d17f8',
            title: 'Go To Statement Considered Harmful',
            author: 'Edsger W. Dijkstra',
            url: 'http://www.u.arizona.edu/~rubinson/copyright_violations/Go_To_Considered_Harmful.html',
            likes: 5,
            __v: 0
        },
        {
            _id: '5a422aa71b51a676234d17f8',
            title: 'Go atement Considered Harmful',
            author: 'EdsgeDijkstra',
            url: 'http://www.u.arizona.edu/~rubinson/copyright_violations/Go_To_Considered_Harmful.html',
            likes: 10,
            __v: 1
        }
    ]
    test('multiple blogs', () => {
        expect(totalLikes(listWithMoreBlogs)).toBe(15)
    })

    describe('favourite blog', () => {
        test('no blog', () => {
            expect(favouriteBlog([])).toEqual({likes: 0})
        })
        const listWithOneBlog = [
            {
                title: 'Go To Statement Considered Harmful',
                author: 'Edsger W. Dijkstra',
                likes: 12,
            }
        ]
        test('one blog', () => {
            expect(favouriteBlog(listWithOneBlog)).toEqual({
                title: "Go To Statement Considered Harmful",
                author: "Edsger W. Dijkstra",
                likes: 12
            })
        })
    })
    const listWithMoreBlogss = [
        {
            _id: '5a422aa71b54a676234d17f8',
            title: 'Go To Statement Considered Harmful',
            author: 'Edsger W. Dijkstra',
            url: 'http://www.u.arizona.edu/~rubinson/copyright_violations/Go_To_Considered_Harmful.html',
            likes: 5,
            __v: 0
        },
        {
            _id: '5a422aa71b51a676234d17f8',
            title: 'Go atement Considered Harmful',
            author: 'EdsgeDijkstra',
            url: 'http://www.u.arizona.edu/~rubinson/copyright_violations/Go_To_Considered_Harmful.html',
            likes: 10,
            __v: 1
        }
    ]
    test('more blogs', () => {
        expect(favouriteBlog(listWithMoreBlogss)).toEqual({
            _id: '5a422aa71b51a676234d17f8',
            title: 'Go atement Considered Harmful',
            author: 'EdsgeDijkstra',
            url: 'http://www.u.arizona.edu/~rubinson/copyright_violations/Go_To_Considered_Harmful.html',
            likes: 10,
            __v: 1
        })
    })
})

test('blogs are returned as JSON', async () => {
    await api
        .get('/api/blogs')
        .expect(200)
        .expect('Content-Type', /application\/json/)
})

/*test('there are 2 blogs', async () => {
    const response = await api.get('/api/blogs')
    expect(response.body).toHaveLength(initialBlogs.length)
})*/

test('unique identifier', async () => {
    const response = await api.get('/api/blogs')
    response.body.forEach(x => {
        expect(x.id).toBeDefined()
    })
})

test('a valid blog can be added', async () => {
    const newBlog = {
        title: 'Test blog',
        author: 'Lavinuta',
        url: 'Mihai.Enat.ro',
        likes: 10000,
    }
    await api
        .post('/api/blogs')
        .send(newBlog)
        .expect(200)
        .expect('Content-Type', /application\/json/)
    const blogsAtEnd = await api.get('/api/blogs')
    expect(blogsAtEnd.body).toHaveLength(initialBlogs.length + 1)
})

/*test('testing if likes property are missing',async () => {
    const newBlog = {
        title: 'Test',
        author: 'Test',
        url: 'test'
    }

    expect(newBlog.likes).toBeUndefined()

    await  api
        .post('/api/blogs')
        .send(newBlog)
    const blogss = await api.get('/api/blogs')
    blogss.body.forEach(x => {
        expect(x.likes).toBeDefined()
    })
})*/

test ('testing creating new blogs missing url and title', async () => {
    const newBlog = {
        author: 'Test',
        likes: 100
    }
    await api
        .post('/api/blogs')
        .send(newBlog)
        .expect(400)
})

test('deleting a single note',async () => {
    const blogsAtStart = await api.get('/api/blogs')
    const blogToDelete = blogsAtStart.body[0]
    console.log(blogToDelete)
    await api
        .delete(`/api/blogs/${blogToDelete.id}`)
        .expect(204)

    const blogsAtEnd = await api.get('/api/blogs')
    expect(blogsAtEnd.body).toHaveLength(blogsAtStart.body.length - 1)
})

test('updating a blog',async () => {
    const blogsAtStart = await api.get('/api/blogs')
    console.log(blogsAtStart.body)
    let blogToBeUpdated = blogsAtStart.body[0]
    blogToBeUpdated.likes = 999
    const blogsAtEnd = await api.get('/api/blogs')
    await api
        .put(`/api/blogs/${blogToBeUpdated.id}`)
    expect(blogsAtStart.body).toEqual(blogsAtEnd.body)

})

afterAll(() => {
    mongoose.connection.close()
})