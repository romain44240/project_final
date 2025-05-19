FROM node:20 AS build

WORKDIR /app

COPY . .
# si problème de cache
# RUN rm -rf node_modules package-lock.json

RUN npm install jwt-decode && npm install

RUN npm run build --prod

FROM nginx:alpine

COPY /nginx/nginx.conf /etc/nginx/conf.d/default.conf

COPY --from=build /app/dist/projet-angular/browser /usr/share/nginx/html

EXPOSE 80
