import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        lanchas: [],
        rentas: [],
        precios: [],
        error: '',
        usosActivos: [],
        personasEspera: [],
        diarios: []
    },
    mutations: {
        setLanchas(state, lanchas) {
            state.lanchas = lanchas;
        },
        addLancha(state, lancha) {
            state.lanchas.push(lancha);
        },
        updateLancha(state, lancha) {
            let indx = state.lanchas.findIndex(el => el.id === lancha.id);
            state.lanchas[indx] = lancha;
        },
        manejarError(state, error) {
            console.error(error);
            if(error == 'Error: Network Error') {
                state.error = 'Error de conexión';
            } else {
                state.error = error;
            }
        },
        setRentas(state, rentas) {
            state.rentas = rentas;
        },
        setPrecios(state, precios) {
            state.precios = precios;
        },
        addPrecio(state, precio) {
            state.precios.push(precio);
        },
        updatePrecio(state, precio) {
            let indx = state.precios.findIndex(el => el.id === precio.id);
            state.precios[indx] = precio;
        },
        deletePrecio(state, precio) {
            state.precios.splice(state.precios.findIndex(el => el.id === precio.id), 1);
            Vue.swal.fire('¡Eliminado!', 'El precio ha sido eliminado.', 'success');
        },
        quitarRenta(state, renta) {
            state.rentas.splice(state.rentas.findIndex(el => el.id === renta.id), 1);
            Vue.swal.fire('¡Renta Terminada!', 'La renta se ha terminado con éxito.', 'success');
        },
        updateRenta(state, renta) {
            let indx = state.rentas.findIndex(el => el.id === renta.id);
            state.rentas[indx] = renta;
            Vue.swal.fire('¡Renta Actualizada!', 'La renta se ha actualizado con éxito.', 'success');
        },
        setUsosActivos(state, usos) {
            state.usosActivos = usos;
        },
        addUsoActivo(state, uso) {
            state.usosActivos.push(uso);
        },
        setError(state, error) {
            state.error = error;
        },
        addPersonaListaEspera(state, persona) {
            state.personasEspera.push(persona);
        },
        setListaEspera(state, personas) {
            state.personasEspera = personas;
        },
        updatePersonaListaEspera(state, persona) {
            let indx = state.personasEspera.findIndex(el => el.id === persona.id);
            state.personasEspera[indx] = persona;
        },
        removePersonaListaEspera(state, persona) {
            state.personasEspera.splice(state.personasEspera.findIndex(el => el.id === persona.id), 1);
            Vue.swal.fire('¡Persona eliminada!', 'Persona eliminada de la lista de espera con éxito.', 'success');
        },
        setDiarios(state, diarios) {
            state.diarios = diarios;
        },
        addDiario(state, diario) {
            state.diarios.push(diario);
            Vue.swal.fire('¡Diario agregado!', 'Diario agregado éxitosamente.', 'success');
        },
        updateDiario(state, diario) {
            let indx = state.diarios.findIndex(el => el.id === diario.id);
            state.diarios[indx] = diario;
        },
        removeDiario(state, diario) {
            state.diarios.splice(state.diarios.findIndex(el => el.id === diario.id), 1);
            Vue.swal.fire('¡Diario eliminado!', 'Diario eliminado de la lista con éxito.', 'success');
        },
    },
    // TODO: Separar por modulos
    actions: {
        getLanchas({commit}) {
        // TODO: Poner en señal de que se están cargando las lanchas
        axios.get('/lancha')
            .then(res => {
                console.log('Lanchas obtenidas.');
                commit('setLanchas', res.data);
            })
            .catch(error => {
                commit('manejarError', error);
            });
        // TODO: Quitar la señal de carga de lanchas
        },
        nuevaLancha({commit}, payload) {
        axios.post('/lancha', {
            numero: payload.numero,
            nombre: payload.nombre
        }).then(res => {
                // TODO: mostrar alerta de que se ha agregado el nuevo mensaje
                commit('addLancha', res.data);
            })
            .catch(error => {
                commit('manejarError', error);
            });
        },
        actualizarLancha({commit}, payload) {
            let lancha = payload.lancha;
            lancha.numero = payload.numero;
            lancha.nombre = payload.nombre;
            lancha.estado = payload.estado;
            axios.put(`/lancha/${payload.lancha.id}`, lancha)
                .then(res => {
                    commit('updateLancha', res.data);
                    console.log('Lancha actualizada');
                }).catch(error => {
                    commit('manejarError', error);
                });
        },
        obtenerUsosDeRentasActivas({commit}) {
            axios.get('/uso/de-renta-activa')
                .then(res => {
                    console.log('Usos de rentas de activas: ', res.data);
                    commit('setUsosActivos', res.data);
                })
                .catch(error => {
                    commit('manejarError', error);
                });
        },
        nuevoUso({commit}, payload) {
            // TODO: Cambiar la manera como se manda el post como el de nueva renta.
            axios.post('/uso', payload)
                .then(res => {
                    console.log('Uso agregado.');
                    commit('addUsoActivo', res.data);
                    // TODO: hacer que el tiempo agregado se ponga en la parte de las rentas
                })
                .catch(error => {
                    commit('manejarError', error);
                });
        },
        nuevaRenta({commit, dispatch}, payload) {
            const date = new Date();
            const format = new Intl.DateTimeFormat('es', { year: 'numeric', month: '2-digit', day: '2-digit' });
            const [{value: day}, , {value: month}, , {value: year}] = format.formatToParts(date);
            const fecha = `${day}/${month}/${year}`; // fecha formateada 'XX/XX/XXXX'
            let data = {
                lancha_id: payload.lancha.id,
                fecha,
                c_adultos: payload.adl,
                c_jovenes: payload.jov
            }
            axios.post('/renta', data)
                .then(res => {
                  dispatch('nuevoUso', { renta_id: res.data.id, tiempo: payload.precio.tiempo, precio: payload.precio.precio });
                }).catch(error => {
                  commit('manejarError', error);
                });
        },
        obtenerRentas({commit}) {
            axios.get('/renta/activo')
                .then(res => {
                    console.log('Rentas obtenidas.');
                    commit('setRentas', res.data);
                }).catch(error => {
                    commit('manejarError', error);
                });
        },
        obtenerPrecios({commit}) {
            axios.get('/precios/activo')
                .then(res => {
                    console.log('Precios obtenidos.');
                    commit('setPrecios', res.data);
                }).catch(error => {
                    commit('manejarError', error);
                });
        },
        nuevoPrecio({commit}, payload) {
            const date = new Date();
            const format = new Intl.DateTimeFormat('es', { year: 'numeric', month: '2-digit', day: '2-digit' });
            const [{value: day}, , {value: month}, , {value: year}] = format.formatToParts(date);
            const fecha = `${day}/${month}/${year}`; // fecha formateada 'XX/XX/XXXX'
            axios.post('/precios', {
                tiempo: payload.tiempo,
                precio: payload.precio,
                estado: true,
                fecha
            }).then(res => {
                commit('addPrecio', res.data);
            }).catch(error => {
                commit('manejarError', error);
            });
        },
        actualizarPrecio({commit}, payload) {
            axios.put(`/precios/${payload.id}`, {
                tiempo: payload.tiempo,
                precio: payload.precio,
                estado: payload.estado
            }).then(res => {
                commit('updatePrecio', res.data);
            }).catch(error =>{
                commit('manejarError', error);
            });
        },
        eliminarPrecio({commit}, payload) {
            axios.delete(`/precios/${payload.precio.id}`)
                .then(res => {
                    console.log(res.data);
                    commit('deletePrecio', payload.precio);
                }).catch(error => {
                    commit('manejarError', error);
                });
        },
        terminarRenta({commit, dispatch}, payload) {
            axios.put(`/renta/update-uso/${payload.renta.id},${payload.en_uso}`)
                .then(res => {
                    let lancha = res.data.lancha;
                    dispatch('actualizarLancha', {lancha, numero: lancha.numero, nombre: lancha.nombre, estado: 0});
                    commit('quitarRenta', res.data);
                    dispatch('obtenerUsosDeRentasActivas');
                    console.log('Renta terminada.')
                }).catch(error => {
                    commit('manejarError', error);
                });
        },
        actualizarRenta({commit}, payload) {
            let renta = payload.renta;
            renta.c_adultos = payload.c_adultos;
            renta.c_jovenes = payload.c_jovenes;
            renta.observaciones = payload.observaciones;
            axios.put(`/renta/${payload.renta.id}`, renta)
                .then(res => {
                    commit('updateRenta', res);
                    console.log('Renta actualizada');
                }).catch(error => {
                    commit('manejarError', error);
                });
        },
        nuevaPersonaEspera({commit}, payload) {
            axios.post('/lista-personas', {
                nombre: payload.nombre
            }).then(res => {
                commit('addPersonaListaEspera', res.data);
                console.log('Persona agregada a la lista de espera');
            }).catch(error => {
                commit('manejarError', error);
            });
        },
        obtenerListaEspera({commit}) {
            axios.get('/lista-personas')
                .then(res => {
                    console.log('Obtenida lista de espera', res.data);
                    commit('setListaEspera', res.data);
                })
                .catch(error => {
                    commit('manejarError', error);
                });
        },
        actualizarPersonaListaEspera({commit}, payload) {
            let persona = payload.persona;
            persona.nombre = payload.nombre;
            axios.put(`/lista-personas/${payload.persona.id}`,  persona)
                .then(res => {
                    commit('updatePersonaListaEspera', res.data);
                }).catch(error =>{
                    commit('manejarError', error);
                });
        },
        eliminarPersonaListaEspera({commit}, payload) {
            axios.delete(`/lista-personas/${payload.persona.id}`)
                .then(res => {
                    commit('removePersonaListaEspera', payload.persona);
                    console.log(res.data);
                }).catch(error =>{
                    commit('manejarError', error);
                });
        },
        obtenerDiarios({commit}) {
            axios.get('/diario')
                .then(res =>{
                    console.log('Diarios obtenidos');
                    commit('setDiarios', res.data);
                }).catch(error => {
                    commit('manejarError', error);
            });
        },
        nuevoDiario({commit}, payload) {
            axios.post('/diario', payload)
                .then(res => {
                    commit('addDiario', res.data);
                }).catch(error => {
                    commit('manejarError', error);
            });
        },
        eliminarDiario({commit}, payload) {
            axios.delete(`/diario/${payload.diario.id}`)
                .then(res => {
                    commit('removeDiario', payload.diario);
                    console.log(res.data);
                }).catch(error => {
                    commit('manejarError', error);
            });
        },
        actualizarDiario({commit}, payload) {
            let diario = payload.diario;
            diario.descripcion = payload.descripcion;
            axios.put(`/diario/${payload.diario.id}`, diario)
                .then(res => {
                    commit('updateDiario', res.data);
                }).catch(error => {
                    commit('manejarError', error);
            })
        }
    },
    modules: {
    }
})
