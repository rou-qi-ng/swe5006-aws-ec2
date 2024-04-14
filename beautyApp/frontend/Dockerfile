# Use an official Node.js runtime as the base image
FROM node:16 as build-stage

# Set the working directory
WORKDIR /app

# Copy package.json and install dependencies
COPY package.json ./
RUN npm install

# Copy the Angular app source code
COPY . .

# Build the Angular app
RUN npm run build --prod

# Use an Nginx image to serve the built app
FROM nginx:alpine

# Copy the build output to the Nginx web directory
COPY --from=build-stage /app/dist/your-angular-app /usr/share/nginx/html

# Expose the port Nginx is running on
EXPOSE 80

# Start Nginx when the container is run
CMD ["nginx", "-g", "daemon off;"]
