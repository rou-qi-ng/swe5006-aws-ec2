const express = require('express');
const path = require('path');

const app = express();

// Serve static files from the Angular build output directory
app.use(express.static(path.join(__dirname, 'dist', 'frontend', 'browser')));

// Redirect all requests to the Angular app
app.get('*', (req, res) => {
    res.sendFile(path.join(__dirname, 'dist', 'frontend', 'browser', 'index.html'));
});

// Listen on a specific port
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});