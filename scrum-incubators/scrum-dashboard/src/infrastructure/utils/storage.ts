
class LocalStorage {

	public static storageInstance: LocalStorage;

	private constructor() {}

	public static createInstance() {
		if (!this.storageInstance) {
			this.storageInstance = new LocalStorage()
		}
		return this.storageInstance
	}

	public getItem(key: string) {
		const localDataStr = localStorage.getItem(key)
		if (!localDataStr) return null;
		return JSON.parse(localDataStr)
	}

	public setItem(key: string, value: any) {
		localStorage.setItem(key, JSON.stringify(value));
	}

	public removeItem(key: string){
		localStorage.removeItem(key)
	}

	public clear() {
		localStorage.clear()
	}

}

export default LocalStorage.createInstance();