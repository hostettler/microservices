import { DynamicEnvironment } from './dynamic-environment';
class Environment extends DynamicEnvironment {
  public production: boolean;
  constructor() {
    super();
  }
}

export const environment = new Environment();
