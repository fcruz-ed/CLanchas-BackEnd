import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
        path: '/', name: 'Home',
        component: () => import(/* webpackChunkName: "home" */ '../views/Home.vue')
    },
    {
        path: '/about', name: 'About',
        component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
    },
    {
        path: '/lanchas', name: 'Lanchas',
        component: () => import(/* webpackChunkName: "lancha" */ '../views/Lanchas.vue')
    },
    {
        path: '/rentas', name: 'Rentas',
        component: () => import(/* webpackChunkName: "renta" */ '../views/Rentas.vue')
    },
    {
        path: '/precios', name: 'Precios',
        component: () => import(/* webpackChunkName: "precios" */ '../views/Precios.vue')
    },
    {
        path: '/espera', name: 'Espera',
        component: () => import(/* webpackChunkName: "espera" */ '../views/Espera.vue')
    },
    {
        path: '/reportes', name: 'Reportes',
        component: () => import(/* webpackChunkName: "reportes" */ '../views/Reportes.vue')
    },
    {
        path: '/diario', name: 'Diarios',
        component: () => import(/* webpackChunkName: "diarios" */ '../views/Diarios.vue')
    },
    { path: '**', redirect: '/' }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
