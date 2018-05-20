FROM node:9.11.1-alpine

# Create app directory
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

# Install app dependencies
RUN apk update && apk upgrade && apk add git

ONBUILD COPY . /usr/src/app/
ONBUILD RUN npm install

# Build app
ONBUILD RUN npm run prisma-deploy

ENV HOST 0.0.0.0
EXPOSE 4000

# start command
CMD [ "npm", "start" ]
