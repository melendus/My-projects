import React, {useState, useEffect} from 'react'
import Blog from './components/Blog'
import blogService from './services/blogs'
import loginService from "./services/login";

const App = () => {
    const [blogs, setBlogs] = useState([])
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [user, setUser] = useState(null)
    const [title, setTitle] = useState('')
    const [author, setAuthor] = useState('')
    const [url, setUrl] = useState('')

    useEffect(() => {
        blogService.getAll().then(blogs =>
            setBlogs(blogs)
        )
    }, [])

    useEffect(() => {
        const loggedUserJSON = window.localStorage.getItem('loggedBlogUser');
        if (loggedUserJSON) {
            const user = JSON.parse(loggedUserJSON)
            setUser(user)
        }
    }, [])

    const handleLogin = async (event) => {
        event.preventDefault()

        try {
            const user = await loginService.login({
                username, password,
            })
            window.localStorage.setItem('loggedBlogUser', JSON.stringify(user))
            blogService.setToken(user.token)
            setUser(user)
            setUsername('')
            setPassword('')
        } catch (exception) {
            window.alert('Wrong credentials')
            setTimeout(5000)
        }
    }

    const handleLogout = async () => {
        window.localStorage.clear()
        setUser(null)
    }

    const addBlog = async (event) => {
        event.preventDefault()
        const blogObject = {title: title, author: author, url: url}
        const returnedBlog = await blogService.create(blogObject)
        setBlogs(blogs.concat(returnedBlog))
    }

    const loginForm = () => (
        <form onSubmit={handleLogin}>
            <div>
                username
                <input
                    type="text"
                    value={username}
                    name="Username"
                    onChange={({target}) => setUsername(target.value)}
                />
            </div>
            <div>
                password
                <input
                    type="password"
                    value={password}
                    name="Password"
                    onChange={({target}) => setPassword(target.value)}
                />
            </div>
            <button type="submit">login</button>
        </form>
    )

    const newBlogForm = () => {
        return (
            <form onSubmit={addBlog}>
                <div>
                    <p>
                        title
                        <input type="text"
                               value={title}
                               name="Title"
                               onChange={({target}) => setTitle(target.value)}
                        />
                    </p>
                    <p>
                        author
                        <input
                            type="text"
                            value={author}
                            name="Author"
                            onChange={({target}) => setAuthor(target.value)}
                        />
                    </p>
                    <p>
                        url
                        <input
                            type="text"
                            value={url}
                            name="Url"
                            onChange={({target}) => setUrl(target.value)}
                        />
                    </p>
                </div>
                <button type="submit">create</button>
            </form>
        )
    }

    const logoutForm = () => {
        return (
            <button onClick={() => handleLogout()}>logout</button>
        )
    }

    const showBlogs = () => {
        console.log(blogs)
        return (
            blogs.map(blog =>

                <Blog key={blog.id} blog={blog}/>
            )
        )
    }

    const createNewBlog = () => {
        return (
            <div>
                <h2>create new blog</h2>
                {newBlogForm()}
            </div>
        )
    }

    const userLogged = () => {
        return (
            <div>
                <p>{user.name} logged {logoutForm()}</p>
                {showBlogs()}
                {createNewBlog()}
            </div>
        )
    }

    return (
        <div>
            <h2>blogs</h2>
            {user === null ?
                loginForm() :
                userLogged()
            }
        </div>
    )
}

export default App