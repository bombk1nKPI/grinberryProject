
FROM node:18-alpine AS build

WORKDIR /app

COPY package.json package-lock.json ./

RUN npm install --force

COPY . .

RUN npm run build

FROM nginx:alpine

COPY --from=build app/dist/grinberry-project/browser  /usr/share/nginx/html

COPY nginx.conf /etc/nginx/conf.d/default.conf

COPY ssl/certificate.crt /etc/nginx/ssl/certificate.crt
COPY ssl/private.key /etc/nginx/ssl/private.key

EXPOSE 80
EXPOSE 443

CMD ["nginx", "-g", "daemon off;"]