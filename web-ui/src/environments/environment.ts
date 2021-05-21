import { DynamicEnvironment } from './dynamic-environment';
class Environment extends DynamicEnvironment {
  constructor() {
    super();
  }
}

export const environment = new Environment();
