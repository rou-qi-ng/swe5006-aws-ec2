# # Use an official Node.js runtime as the base image
# FROM node:18 as build-stage

# # Set the working directory
# WORKDIR /app

# # Copy package.json and install dependencies
# COPY package.json ./
# RUN npm install

# # Copy the Angular app source code
# COPY . .

# # Build the Angular app
# RUN npm run build --prod

# # Use an Nginx image to serve the built app
# FROM nginx:alpine

# # Copy the build output to the Nginx web directory
# COPY --from=build-stage /app/dist/frontend /usr/share/nginx/html

# # Expose the port Nginx is running on
# EXPOSE 80

# # Start Nginx when the container is run
# CMD ["nginx", "-g", "daemon off;"]

# Use Node.js as the base image
FROM node:18

# Set the working directory
WORKDIR /app

# Copy package.json and install dependencies
COPY package.json package-lock.json ./
RUN npm install

# Install Express
RUN npm install express

# Copy the Angular app source code
COPY . .

# Build the Angular app
RUN npm run build --prod

# Create an Express server to serve the built Angular app
COPY server.js .

# Expose the port your Express server will listen on
EXPOSE 3000

# Start the Express server when the container is run
CMD ["node", "server.js"]