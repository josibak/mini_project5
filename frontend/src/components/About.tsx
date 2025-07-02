
const About = () => {
  return (
    <section id="about" className="py-20 px-4 sm:px-6 lg:px-8 bg-gray-50">
      <div className="max-w-4xl mx-auto">
        <div className="text-center mb-16">
          <h2 className="text-3xl md:text-4xl font-light text-gray-900 mb-4">
            About Me
          </h2>
          <div className="w-24 h-px bg-gray-400 mx-auto"></div>
        </div>
        
        <div className="grid md:grid-cols-2 gap-12 items-center">
          <div>
            <p className="text-lg text-gray-600 leading-relaxed mb-6">
              I'm a passionate developer with over 5 years of experience creating 
              digital solutions that matter. I specialize in frontend development, 
              user experience design, and bringing ideas to life through code.
            </p>
            <p className="text-lg text-gray-600 leading-relaxed mb-8">
              When I'm not coding, you'll find me exploring new technologies, 
              contributing to open source projects, or sharing knowledge with 
              the developer community.
            </p>
            
            <div className="grid grid-cols-2 gap-6">
              <div>
                <h3 className="font-medium text-gray-900 mb-3">Frontend</h3>
                <ul className="space-y-2 text-gray-600">
                  <li>React & Next.js</li>
                  <li>TypeScript</li>
                  <li>Tailwind CSS</li>
                </ul>
              </div>
              <div>
                <h3 className="font-medium text-gray-900 mb-3">Backend</h3>
                <ul className="space-y-2 text-gray-600">
                  <li>Node.js</li>
                  <li>Python</li>
                  <li>PostgreSQL</li>
                </ul>
              </div>
            </div>
          </div>
          
          <div className="flex justify-center">
            <div className="w-80 h-80 bg-gradient-to-br from-gray-200 to-gray-300 rounded-lg flex items-center justify-center">
              <span className="text-gray-500 text-lg">Profile Image</span>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default About;
