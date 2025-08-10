<template>
    <div id="app">
        <Navigation />
            <!--      TODO: alerta que sea cuando se agregue una nueva lancha o se modifiquen correctamente-->
        <b-alert variant="danger" dismissible>
            {{error}}
        </b-alert>
        <b-container>
            <router-view class="m-2" />
        </b-container>
    </div>
</template>

<script>
    import Navigation from "./components/Navigation";
    import { mapState } from 'vuex';
    export default {
        name: 'App',
        computed: {
            ...mapState(['error'])
        },
        data: () => ({
        }),
        components: {
            Navigation
        }
    }
</script>

<style>

</style>
