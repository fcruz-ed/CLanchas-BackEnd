<template>
    <div>
        <h1>Inicio</h1>
        <div class="mt-2">
            <b-row class="my-4">
                <b-col md="6" sm="12" class="mt-2">
                    <b-card border-variant="success">
                        <b-card-title>Lanchas disponibles ({{estado.disponibles}})</b-card-title>
                        <ul>
                            <li v-for="(lancha, index) in lanchas" :key="index" v-if="lancha.estado === 0">
                                Lancha #{{lancha.numero}}
                            </li>
                        </ul>
                        <hr />
                        <b-button variant="success" :to="{ name: 'Lanchas' }">Ir a lanchas</b-button>
                    </b-card>
                </b-col>
                <b-col md="6" sm="12" class="mt-2">
                    <b-card border-variant="primary">
                        <b-card-title>Lanchas ocupadas ({{estado.ocupadas}})</b-card-title>
                        <ul>
                            <li v-for="(lancha, index) in lanchas" :key="index" v-if="lancha.estado === 1">
                                Lancha #{{lancha.numero}}
                            </li>
                        </ul>
                        <hr />
                        <b-button variant="primary" :to="{ name: 'Rentas' }">Ir a rentas</b-button>
                    </b-card>
                </b-col>
            </b-row>
            <b-row class="my-4">
                <b-col md="6" sm="12" class="mt-2">
                    <b-card border-variant="dark">
                        <b-card-title>Lanchas en reparación ({{estado.reparacion}})</b-card-title>
                        <ul>
                            <li v-for="(lancha, index) in lanchas" :key="index" v-if="lancha.estado === 2">
                                Lancha #{{lancha.numero}}
                            </li>
                        </ul>
                        <hr />
                        <b-button variant="secondary" :to="{ name: 'Lanchas' }">Ir a lanchas</b-button>
                    </b-card>
                </b-col>
                <b-col md="6" sm="12" class="mt-2">
                    <b-card border-variant="dark">
                        <b-card-title>Lanchas en otro estado ({{estado.otro}})</b-card-title>
                        <ul>
                            <li v-for="(lancha, index) in lanchas" :key="index" v-if="lancha.estado > 2">
                                Lancha #{{lancha.numero}}
                            </li>
                        </ul>
                        <hr />
                        <b-button variant="secondary" :to="{ name: 'Lanchas' }">Ir a lachas</b-button>
                    </b-card>
                </b-col>
            </b-row>
            <b-row class="my-4">
                <b-col md="6" sm="12" class="mt-2">
                    <b-card border-variant="info">
                        <b-card-title>Personas es espera ({{estado.personasLista}})</b-card-title>
                        <ul>
                            <li v-for="(persona, index) in personasEspera" :key="index">
                                {{index+1}} - {{persona.nombre}}
                            </li>
                        </ul>
                        <hr />
                        <b-button variant="info" :to="{ name: 'Espera' }">Ir a lista de espera</b-button>
                    </b-card>
                </b-col>
            </b-row>
        </div>
    </div>
</template>

<script>
    import { mapState, mapActions } from 'vuex'
    export default {
        name: 'Home',
        data: () => ({
            estado: { disponibles: 0, ocupadas: 0, reparacion: 0, otro: 0, personasLista: 0 }
        }),
        computed: {
            ...mapState(['lanchas', 'personasEspera']),
        },
        methods: {
            ...mapActions(['getLanchas', 'obtenerRentas', 'obtenerListaEspera']),
            setEstado() {
                this.estado = { disponibles: 0, ocupadas: 0, reparacion: 0, otro: 0, personasLista: 0 }
                this.lanchas.forEach(lancha => {
                    if(lancha.estado === 0) {
                        this.estado.disponibles += 1;
                    }
                    if(lancha.estado === 1) {
                        this.estado.ocupadas += 1;
                    }
                    if(lancha.estado === 2) {
                        this.estado.reparacion += 1;
                    }
                    if(lancha.estado === 3) {
                        this.estado.otro += 1;
                    }
                });
                this.estado.personasLista = this.personasEspera.length;
            }
        },
        created() {
            this.getLanchas();
            this.obtenerListaEspera();
        },
        beforeUpdate() {
            this.setEstado();
        }
    }
</script>
