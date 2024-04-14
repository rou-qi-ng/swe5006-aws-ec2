# # Use an official Node.js runtime as the base image
# FROM node:18 as build-stage

# # Set the working directory
# WORKDIR /app

# RUN mkdir frontend

# WORKDIR frontend

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
# COPY --from=build-stage /app/frontend/dist /usr/share/nginx/html

# # Expose the port Nginx is running on
# EXPOSE 80

# # Start Nginx when the container is run
# CMD ["nginx", "-g", "daemon off;"]



# # Use an official Node.js runtime as the base image
# FROM node:18 as build-stage

# # Set the working directory
# WORKDIR /app

# # Copy package.json and install dependencies
# COPY package.json package-lock.json ./
# RUN npm install

# # Copy the Angular app source code
# COPY . .

# # Build the Angular app
# RUN npm run build --prod

# # Use a different base image (node:18) to serve the built app
# FROM node:18

# # Set the working directory
# WORKDIR /app

# # Copy the build output from the build-stage
# COPY --from=build-stage /app/dist/frontend /app/public

# # Expose the port your server will listen on (e.g., 3000)
# EXPOSE 3000

# # Start the Express server
# CMD ["node", "app.js"]


# # Define the build stage
# FROM node:18 as build-stage

# # Set the working directory
# WORKDIR /app/frontend

# # Copy package.json and package-lock.json
# COPY package.json package-lock.json ./

# # Install dependencies
# RUN npm install

# # Copy the Angular app source code
# COPY . .

# # Build the Angular app
# RUN npm run build --prod

# # Define the runtime stage
# FROM node:18

# # Set the working directory for the runtime stage
# WORKDIR /app

# # Copy the build output from the build stage to the runtime stage
# COPY --from=build-stage /app/frontend/dist/frontend /app/public

# # Copy package.json and package-lock.json
# COPY package.json package-lock.json ./

# # Install dependencies (if any are required in the runtime stage)
# RUN npm install

# # Expose the port your application will run on
# EXPOSE 3000

# # Define the entry point of the application
# CMD ["node", "app.js"]


# Use Node.js base image
FROM node:18

# Set the working directory
WORKDIR /app

# Copy package.json and package-lock.json files and install dependencies
COPY package.json package-lock.json ./
RUN npm install

# Copy the Angular app source code
COPY . .

# Build the Angular app
RUN npm run build --prod

# Install the serve package globally to serve the built Angular application
RUN npm install -g serve

# Expose the port your application will run on (default port for serve is 3000)
EXPOSE 3000

# Start the server and serve the built Angular application
CMD ["serve", "-s", "dist"]