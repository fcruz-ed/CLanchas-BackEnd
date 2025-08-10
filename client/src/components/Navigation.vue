<template>
    <div>
        <b-navbar>
            <b-navbar-brand :to="{ name: 'Home' }">Control Lanchas</b-navbar-brand>

            <b-button class="ml-auto" v-b-toggle.sidebar-backdrop variant="link">
                <i class="fas fa-bars text-dark"></i>
            </b-button>
        </b-navbar>

        <b-sidebar id="sidebar-backdrop" title="Control Lanchas" backdrop-variant="dark" bg-variant="white" right backdrop>
            <div class="py-2">
                <b-list-group flush>
                    <b-list-group-item v-for="(link, index) in links" :to="link.to" :active="link.to.name === $route.name" class="my-1" :key="index">
                        <i :class="[link.icon, 'text-dark', 'mr-2']"></i>
                        {{link.title}}
                    </b-list-group-item>
                </b-list-group>
            </div>
            <template v-slot:footer>
                <div class="d-flex bg-dark text-light px-3 py-2">
                    <strong>UAA</strong>
                </div>
            </template>
        </b-sidebar>
    </div>
</template>

<script>
    export default {
        name: "Navigation",
        data: () => ({
            links: [
                { title: 'Inicio', to: { name: 'Home' }, icon: 'fas fa-home' },
                { title: 'Lanchas', to: { name: 'Lanchas' }, icon: 'fas fa-ship' },
                { title: 'Rentas', to: { name: 'Rentas' }, icon: 'fas fa-file-invoice-dollar'},
                { title: 'Lista de espera', to: { name: 'Espera' }, icon: 'fas fa-list' },
                { title: 'Precios', to: { name: 'Precios' }, icon: 'fas fa-dollar-sign'},
                { title: 'Reportes', to: { name: 'Reportes' }, icon: 'far fa-chart-bar' },
                { title: 'Diario', to: { name: 'Diarios' }, icon: 'fas fa-book' },
                { title: 'Acerca de', to: { name: 'About' }, icon: 'fas fa-info' }
            ]
        })
    }
</script>

<style scoped>

</style>