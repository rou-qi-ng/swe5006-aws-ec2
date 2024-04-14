// Import the express module
const express = require('express');

// Create an instance of an Express application
const app = express();

// Serve the built Angular app from the 'public' directory
app.use(express.static('public'));

// Define a route for the root URL (/)
// This route will serve the Angular app's index.html file
app.get('*', (req, res) => {
    res.sendFile('index.html', { root: 'public' });
});

// Define the port the server will listen on
const PORT = process.env.PORT || 3000;

// Start the server
app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});
