FROM node:carbon

RUN apt-get update && apt-get install -y \
    gettext-base

RUN npm install --quiet --global \
      vue-cli

RUN mkdir /app

COPY package.json /app
RUN yarn install
COPY . /app

EXPOSE 8080

WORKDIR /app

CMD yarn install && yarn serve